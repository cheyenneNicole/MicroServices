package com.example.consumer.service;


import com.example.consumer.model.Consumer;
import com.example.consumer.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService implements BasicService<Consumer> {

    private ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {

        this.consumerRepository = consumerRepository;
    }

    @Override
    public Consumer add(Consumer consumer) {

        return this.consumerRepository.save(consumer);
    }

    @Override
    public Consumer get(Long id) {

        Optional optional = this.consumerRepository.findById(id);

        Consumer result = null;
        if (optional.isPresent()) {
            result = (Consumer) optional.get();
        }
        return result;
    }

    @Override
    public Consumer modify(Consumer consumer) {
        return this.consumerRepository.save(consumer);
    }

    @Override
    public boolean delete(Consumer consumer) {

        this.consumerRepository.delete(consumer);
        return true;
    }

    @Override
    public List<Consumer> getAll() {

        return (List<Consumer>) consumerRepository.findAll();
    }


    public List<Consumer> getConsumerByEmail(String email) {

        return this.consumerRepository.findConsumerByEmail(email);
    }
}

