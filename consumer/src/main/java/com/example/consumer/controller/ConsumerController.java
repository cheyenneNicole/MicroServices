package com.example.consumer.controller;


import com.example.consumer.model.Consumer;
import com.example.consumer.service.ConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@RestController
@RequestMapping("consumerController")
public class ConsumerController{

    private ConsumerService consumerService;


    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }


    @PostMapping(value = "addConsumer", produces = "application/json")
    public Consumer add(@RequestBody Consumer consumer) {

        return this.consumerService.add(consumer);
    }

    @GetMapping(value = "getAllConsumer", produces = "application/json")
    public List<Consumer> getAll() {
        List<Consumer> all = this.consumerService.getAll();
        return all;
    }

    @GetMapping("/getEmail/{email}")
    public Consumer getByEmail(@PathVariable("email") String email){
        Consumer consumer = null;
        try {
            consumer = this.consumerService.getByEmail(email);
        }catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Consumer [" + email + "] Not Found", exc);
        }
        return consumer;
    }

}
