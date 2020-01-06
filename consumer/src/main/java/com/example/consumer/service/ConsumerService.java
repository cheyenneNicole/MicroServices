package com.example.consumer.service;


import com.example.consumer.model.Consumer;
import com.example.consumer.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService{

    private ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {

        this.consumerRepository = consumerRepository;
    }


    public Consumer add(Consumer consumer) {

        return this.consumerRepository.save(consumer);
    }


    public Consumer getByEmail(String emailId) {

       Consumer optional = consumerRepository.findConsumerByEmail(emailId);

        Consumer result = null;
        if (optional != null) {
            result = (Consumer) optional;
        }
        return result;
    }


    public List<Consumer> getAll() {

        return (List<Consumer>) consumerRepository.findAll();
    }

}

