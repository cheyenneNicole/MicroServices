package com.example.salesorder.service;


import com.example.salesorder.models.Consumer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RibbonClient(name = "consumer-service")
@FeignClient(name = "consumer-service")
public interface ConsumerServiceProxy {
    @GetMapping("consumerController/getEmail/{email}")
    Consumer getByEmail(@PathVariable("email") String email);
}
