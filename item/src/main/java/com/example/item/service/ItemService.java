package com.example.item.service;


import com.example.item.model.Item;
import com.example.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Item add(Item consumer) {

        return itemRepository.save(consumer);
    }
    public Item get(String item) {

        Item optional = itemRepository.findItemByName(item);

        Item result = null;
        if (optional != null) {
            result = (Item) optional;
        }
        return result;
    }

    public List<Item> getAll() {

        return (List<Item>) itemRepository.findAll();
    }

}
