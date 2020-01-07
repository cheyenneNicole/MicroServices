package com.example.salesorder.service;

import com.example.salesorder.models.OrderItem;
import com.example.salesorder.repository.OrderItemsRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderItemService {

    private OrderItemsRepo orderItemsRepo;

    public OrderItemService(OrderItemsRepo orderItemsRepo) {

        this.orderItemsRepo = orderItemsRepo;
    }


    public OrderItem add(String itemName, Long orderId, int quantity) {

        OrderItem orderItem = new OrderItem();

        orderItem.setItemName(itemName);
        orderItem.setQuantity(quantity);
        orderItem.setOrderId(orderId);
        return this.orderItemsRepo.save(orderItem);
    }

    public HashMap<String, Integer> getOrdersById(Long id)
    {
        HashMap<String, Integer> hmap = new HashMap<>();

        List<OrderItem> orderListFromTable = this.orderItemsRepo.getOrderItemsByOrderId(id);

        for (OrderItem order: orderListFromTable) {
            hmap.put(order.getItemName(),order.getQuantity());
        }

        return hmap;
    }
}
