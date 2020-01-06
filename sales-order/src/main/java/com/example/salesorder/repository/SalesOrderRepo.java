package com.example.salesorder.repository;


import com.example.salesorder.models.SalesOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalesOrderRepo extends CrudRepository<SalesOrder, Long> {

    List<SalesOrder> findSalesOrderByEmail_id(Long email_id);
}
