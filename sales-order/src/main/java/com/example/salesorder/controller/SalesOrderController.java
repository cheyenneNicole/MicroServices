package com.example.salesorder.controller;



import com.example.salesorder.models.*;
import com.example.salesorder.service.*;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("salesOrderController")
public class SalesOrderController{

    private SalesOrderService salesOrderService;
    private OrderItemService orderItemService;
    private ConsumerService consumerService;
    private ItemService itemService;


        public SalesOrderController(SalesOrderService salesOrderService, ConsumerService consumerService, ItemService itemService,OrderItemService orderItemService) {

            this.salesOrderService = salesOrderService;
            this.consumerService = consumerService;
            this.itemService = itemService;
            this.orderItemService = orderItemService;
        }

    @PostMapping(value = "orders", produces = "application/json")
    public String createOrder(@RequestBody ConsumerInfo orderDetails) {
        String result = "";
        String unavailableItems = "";
        Consumer isCustomerAvailable = null;
        boolean customerEmailAvailable = false;

        isCustomerAvailable = consumerService.getByEmail(orderDetails.getEmail());

        if(isCustomerAvailable.getId() == -1L)
            return "Server is down";

        if (isCustomerAvailable != null)
            customerEmailAvailable = true;

        List<Long> orderIdsList = new ArrayList<>();

        HashMap<String, Integer> hmap = new HashMap<>();

        if (customerEmailAvailable) {
            List<String> orderList = orderDetails.getItemNames();
            List<String> availableList = new ArrayList<>();

            if (orderList.isEmpty() )
                return "Empty order list";

            double totalPrice = 0.0;
            for (String order : orderList) {

                System.out.println(" order: " + order);
                Item item = null;
                item = itemService.get(order);
                System.out.println("item: "+item);

                if (item == null) {
                    unavailableItems = unavailableItems + " " + order;
                    continue;
                }

                else if(item.getId() == -1L)
                {
                    return "Item server is down";

                }

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
                OrderItem orderRes = this.orderItemService.add(entry.getKey(), salesRes.getId(), (int)entry.getValue());
            }


            result = "Order Id is " + result + salesRes.getId();

            if(unavailableItems.length() > 1)
                result =  result + "Unavailable Items: " + unavailableItems;
        }
        return result;
    }

    @GetMapping(value = "orderDetailsByEmail/{email}", produces = "application/json")
    public List<HashMap<String,Integer>> getOrderDetailsByEmail(@PathVariable String email){
        HashMap<String, Integer> hmap = new HashMap<>();
        List<HashMap<String,Integer>> finalList = new ArrayList<>();
        List<SalesOrder> orderIdIs = this.salesOrderService.getOrderIdByEmail(email);
        for (SalesOrder salesOrder: orderIdIs) {
            hmap = this.orderItemService.getOrdersById(salesOrder.getId());
            finalList.add(hmap);
        }
        return finalList;

    }
}
