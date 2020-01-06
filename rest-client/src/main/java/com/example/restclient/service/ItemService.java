package com.example.restclient.service;


import com.example.restclient.model.Item;
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
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    private RestTemplate restTemplate;

    public ItemService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Item saveItem(Item item) {

        URI uri = URI.create("http://localhost:9192/itemapi/item/addItem");

        Item response = restTemplate.postForObject(uri, item, Item.class);

        log.info(new StringBuilder().append("Saved Item: ").append(response.toString()).toString());
        return response;

    }

    public List<Item> getAllItems() {

        URI uri = URI.create("http://localhost:9192/itemapi/item/getAllItems");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Item> items = mapper.convertValue(response, new TypeReference<List<Item>>() {
        });

        log.info(new StringBuilder().append("Returning all items.").toString());
        return items;

    }

    public List<Item> getItemByName(String name) {

        // string replacement needed to create proper URL
        String modifiedName = name.replaceAll(" ", "%20");
        URI uri = URI.create("http://localhost:9192/itemapi/item/getItemByName/" + modifiedName);

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Item> items = mapper.convertValue(response, new TypeReference<List<Item>>() {
        });

        log.info(new StringBuilder().append("Returning item [").append(name).append("].").toString());

        return items;

    }
}

