package com.example.salesorder.controller;


import com.example.salesorder.models.SalesOrder;
import com.example.salesorder.service.SalesOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("salesOrder")
public class SalesOrderController implements BasicController<SalesOrder> {

        private SalesOrderService salesOrderService;


        public SalesOrderController(SalesOrderService salesOrderService) {
            this.salesOrderService = salesOrderService;
        }

        @Override
        @PostMapping(value = "addSalesOrder", produces = "application/json")
        public SalesOrder add(@RequestBody SalesOrder salesOrder) {

            return this.salesOrderService.add(salesOrder);
        }

        @Override
        @GetMapping(value = "getBySalesOrderId/{id}", produces = "application/json")
        public SalesOrder get(@PathVariable("id") Long id) {

            // Demonstrates exception handling with ResponseStatusException exception
            SalesOrder salesOrder = null;
            try {
                salesOrder = this.salesOrderService.get(id);
            } catch (Exception exc) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Consumer [" + id + "] Not Found", exc);
            }
            return salesOrder;
        }

        @Override
        @PutMapping(value = "updateSalesOrder", produces = "application/json")
        public SalesOrder modify(@RequestBody SalesOrder salesOrder) {

            return this.salesOrderService.modify(salesOrder);
        }

        @Override
        @RequestMapping(value = "deleteSalesOrder", method = {RequestMethod.DELETE}, produces = "application/json")
        public boolean delete(@RequestBody SalesOrder salesOrder) {

            return this.salesOrderService.delete(salesOrder);
        }

        @Override
        @GetMapping(value = "getAllSalesOrder", produces = "application/json")
        public List<SalesOrder> getAll() {

            List<SalesOrder> all = this.salesOrderService.getAll();
            return all;
        }

        @GetMapping(value = "getSalesOrderByEmail_id")
        public List<SalesOrder> getSalesOrderByEmail_id(@RequestBody Long email_id) {

            return this.salesOrderService.getSalesOrderByEmail_id(email_id);
        }

    }
