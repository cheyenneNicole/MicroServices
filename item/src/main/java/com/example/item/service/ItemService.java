package com.example.item.service;


import com.example.item.model.Item;
import com.example.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements BasicService<Item> {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @Override
    public Item add(Item item) {

        return itemRepository.save(item);
    }

    @Override
    public Item get(Long id) {

        Optional optional = itemRepository.findById(id);

        Item result = null;
        if (optional.isPresent()) {
            result = (Item) optional.get();
        }
        return result;
    }

    @Override
    public Item modify(Item item) {

        return itemRepository.save(item);
    }

    @Override
    public boolean delete(Item item) {

        itemRepository.delete(item);
        return true;
    }

    @Override
    public List<Item> getAll() {

        return (List<Item>) itemRepository.findAll();
    }

    public List<Item> getAllItemsForConsumer(Long consumerId) {

        return this.itemRepository.getItemsByConsumerId(consumerId);
    }

    public List<Item> getItemByName(String name) {

        return this.itemRepository.getItemByName(name);
    }
}
