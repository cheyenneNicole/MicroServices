package com.example.restclient.service;


import com.example.restclient.model.Consumer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private RestTemplate restTemplate;

    public ConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Consumer saveConsumer(Consumer consumer) {

        URI uri = URI.create("http://localhost:9191/consumerapi/consumer/addConsumer");

        Consumer response = restTemplate.postForObject(uri, consumer, Consumer.class);

        log.info(new StringBuilder().append("Saved Consumer: ").append(response.toString()).toString());
        return response;

    }

    public List<Consumer> getAllConsumers() {

        URI uri = URI.create("http://localhost:9191/customerapi/customer/getAllCustomers");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Consumer> consumers = mapper.convertValue(response, new TypeReference<List<Consumer>>() {
        });

        log.info(new StringBuilder().append("Returning all consumers.").toString());
        return consumers;

    }

    public List<Consumer> getConsumerByEmail(String email) {


        String modifiedName = email.replaceAll(" ", "%20");
        URI uri = URI.create("http://localhost:9191/consumerapi/consumer/getConsumerByEmail/" + modifiedName);

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Consumer> consumers = mapper.convertValue(response, new TypeReference<List<Consumer>>() {
        });

        log.info(new StringBuilder().append("Returning consumer [").append(email).append("].").toString());

        return consumers;

    }


}

