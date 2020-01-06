package com.example.salesorder.service;

import com.example.salesorder.models.SalesOrder;
import com.example.salesorder.repository.SalesOrderRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService{

    private SalesOrderRepo salesOrderRepo;

    public SalesOrderService(SalesOrderRepo salesOrderRepository) {

        this.salesOrderRepo = salesOrderRepo;
    }

    public SalesOrder add(Date date, String email, String description, Double price) {

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setPrice(price);
        salesOrder.setDate(date);
        salesOrder.setDescription(description);
        salesOrder.setEmail(email);

        return this.salesOrderRepo.save(salesOrder);
    }


    public List<SalesOrder> getOrderByEmail(String email) {
        List<SalesOrder> salesOrder = null;
        salesOrder = this.salesOrderRepo.findAllByEmail(email);
        return salesOrder;
    }
}

