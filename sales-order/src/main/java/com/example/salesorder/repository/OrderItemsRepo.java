package com.example.salesorder.repository;

import com.example.salesorder.models.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemsRepo extends CrudRepository<OrderItem, Long> {
    List<OrderItem> getOrderItemsByOrderId(Long id);
}
