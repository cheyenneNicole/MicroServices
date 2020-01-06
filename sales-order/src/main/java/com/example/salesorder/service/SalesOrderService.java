package com.example.salesorder.service;

import com.example.salesorder.models.SalesOrder;
import com.example.salesorder.repository.SalesOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService implements BasicService<SalesOrder> {

    private SalesOrderRepo salesOrderRepository;

    public SalesOrderService(SalesOrderRepo salesOrderRepository) {

        this.salesOrderRepository = salesOrderRepository;
    }

    @Override
    public SalesOrder add(SalesOrder salesOrder) {

        return this.salesOrderRepository.save(salesOrder);
    }

    @Override
    public SalesOrder get(Long id) {

        Optional optional = this.salesOrderRepository.findById(id);

        SalesOrder result = null;
        if (optional.isPresent()) {
            result = (SalesOrder) optional.get();
        }
        return result;
    }

    @Override
    public SalesOrder modify(SalesOrder salesOrder) {
        return this.salesOrderRepository.save(salesOrder);
    }

    @Override
    public boolean delete(SalesOrder salesOrder) {

        this.salesOrderRepository.delete(salesOrder);
        return true;
    }

    @Override
    public List<SalesOrder> getAll() {

        return (List<SalesOrder>) salesOrderRepository.findAll();
    }


    public List<SalesOrder> getSalesOrderByEmail_id(Long email_id) {

        return this.salesOrderRepository.findSalesOrderByEmail_id(email_id);
    }
}

