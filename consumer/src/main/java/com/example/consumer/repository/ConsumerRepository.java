package com.example.consumer.repository;

import com.example.consumer.model.Consumer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {

    Consumer findConsumerByEmail(String email);
}

