package com.example.salesorder.controller;


import com.example.item.service.ItemService;
import com.example.salesorder.models.SalesOrder;
import com.example.salesorder.service.SalesOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("salesOrder")
public class SalesOrderController{

    private SalesOrderService salesOrderService;
    private OrderService orderService;
    private ConsumerService consumerService;
    private ItemService itemService;


        public SalesOrderController(SalesOrderService salesOrderService, ConsumerService consumerService, ItemService itemService) {

            this.salesOrderService = salesOrderService;
            this.consumerService = consumerService;
            this.itemService = itemService;
        }

    @PostMapping(value = "orders", produces = "application/json")
    public String createOrder(@RequestBody customDetails orderDetails) {
        String result = "";
        String unavailableItems = "";
        Consumer isCustomerAvailable = null;
        boolean customerEmailAvailable = false;
//        isCustomerAvailable = customerServiceProxy.getByEmail(orderDetails.getEmail());
        isCustomerAvailable = customerService.getByEmail(orderDetails.getEmail());

        if(isCustomerAvailable.getId() == -1L)
            return "**** Oops... Customer Server is down  ******";

        if (isCustomerAvailable != null)
            customerEmailAvailable = true;

        List<Long> orderIdsList = new ArrayList<>();

//        Long orderIdCreated = null;

        HashMap<String, Integer> hmap = new HashMap<>();

        if (customerEmailAvailable) {
            List<String> orderList = orderDetails.getItemNames();
            List<String> availableList = new ArrayList<>();

            System.out.println("orderList is " + orderList + " list empty check " + orderList.size());

            if (orderList.isEmpty() )
                return "**** Oops... There are no items in the list or items are currently ******";

            double totalPrice = 0.0;
            for (String order : orderList) {

                System.out.println(" order passing to order service is " + order);
                Item item = null;
                item = itemService.get(order);
                System.out.println("returned object from item service is " + item);

                if (item == null) {
                    //result = result + "**** Oops... Item \'" + order + "\' are currently unavailable ******";
                    unavailableItems = unavailableItems + " " + order;
                    continue;
                }

                else if(item.getId() == -1L)
                {
                    return "**** Oops... Item Server is down  ******";

                }

//                System.out.println("---Item Details ----" + item);

                else {
                    totalPrice = totalPrice + item.getPrice();
                    availableList.add(item.getName());
                    if(hmap.containsKey(item.getName()))
                    {
                        int prev = hmap.get(item.getName());
                        prev++;
                        hmap.put(item.getName(), prev);
                    }
                    else
                    {
                        hmap.put(item.getName(), 1);
                    }
                }
            }
            SalesOrder salesRes = null;

            if(totalPrice == 0.0)
                return result;
            else
                salesRes = this.salesOrderService.add(orderDetails.getDate(), orderDetails.getEmail(), orderDetails.getDescription(), totalPrice);


            for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
                System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
                OrderLineItem orderRes = this.orderLineItemService.add(entry.getKey(), salesRes.getId(), (int)entry.getValue());
            }


            result = "Order Id is " + result + salesRes.getId();

            if(unavailableItems.length() > 1)
                result =  result + " & some Items are unavailable and they are " + unavailableItems;
        }
        return result;
    }

    @GetMapping(value = "orderDetailsByEmail/{email}", produces = "application/json")
    public List<HashMap<String,Integer>> getOrderDetailsByEmail(@PathVariable String email){

        LOG.log(Level.INFO, "You reached order by email method");
        HashMap<String, Integer> hmap = new HashMap<>();
        List<HashMap<String,Integer>> finalList = new ArrayList<>();
        List<SalesOrder> orderIdIs = this.salesOrderService.getOrderIdByEmail(email);
        System.out.println("------orderIdIs---------" + orderIdIs);

        System.out.println("---Calling salesorder Service with orderId" );

        for (SalesOrder salesOrder: orderIdIs) {
            hmap = this.orderLineItemService.getOrdersById(salesOrder.getId());
            finalList.add(hmap);
        }
        return finalList;

    }
}
    }
