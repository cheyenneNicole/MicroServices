package com.example.consumer.controller;


import com.example.consumer.model.Consumer;
import com.example.consumer.service.ConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@RestController
@RequestMapping("consumer")
public class ConsumerController implements BasicController<Consumer> {

    private ConsumerService consumerService;


    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Override
    @PostMapping(value = "addConsumer", produces = "application/json")
    public Consumer add(@RequestBody Consumer consumer) {

        return this.consumerService.add(consumer);
    }

    @Override
    @GetMapping(value = "getById/{id}", produces = "application/json")
    public Consumer get(@PathVariable("id") Long id) {

        // Demonstrates exception handling with ResponseStatusException exception
        Consumer consumer = null;
        try {
            consumer = this.consumerService.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Consumer [" + id + "] Not Found", exc);
        }
        return consumer;
    }

    @Override
    @PutMapping(value = "updateConsumer", produces = "application/json")
    public Consumer modify(@RequestBody Consumer consumer) {

        return this.consumerService.modify(consumer);
    }

    @Override
    @RequestMapping(value = "deleteConsumer", method = {RequestMethod.DELETE}, produces = "application/json")
    public boolean delete(@RequestBody Consumer consumer) {

        return this.consumerService.delete(consumer);
    }

    @Override
    @GetMapping(value = "getAllConsumer", produces = "application/json")
    public List<Consumer> getAll() {

        List<Consumer> all = this.consumerService.getAll();
        return all;
    }

    @GetMapping(value = "getConsumerByEmail/{email}", produces = "application/json")
    public List<Consumer> getConsumerByEmail(@PathVariable("email") String email) {

        return this.consumerService.getConsumerByEmail(email);
    }

}
