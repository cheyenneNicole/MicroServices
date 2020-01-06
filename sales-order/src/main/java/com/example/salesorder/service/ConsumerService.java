package com.example.salesorder.service;

import com.example.salesorder.models.Consumer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private ConsumerServiceProxy consumerServiceProxy;

    public ConsumerService(ConsumerServiceProxy consumerServiceProxy){
        this.consumerServiceProxy = consumerServiceProxy;
    }

    @HystrixCommand(fallbackMethod = "defaultConsumerService")
    public Consumer getByEmail(String email)
    {
        return consumerServiceProxy.getByEmail(email);
    }

    private Consumer defaultConsumerService(String email) {
        Consumer consumer = new Consumer();

        consumer.setEmail(email);
        consumer.setId(-1L);

        return consumer;
    }
}
