package com.example.item.service;

import com.example.item.model.Consumer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="zuul-gateway-server")
@RibbonClient(name="consumer-com.example.consumer.com.example.restclient.com.example.consumer.com.example.consumer.com.example.salesorder.service")
public interface ConsumerServiceProxy {

    @GetMapping("consumer-service/consumerapi/consumer/getById/{id}")
    Consumer getConsumerById(@PathVariable("id") long id);
}