package com.example.item.controller;

import com.example.item.model.Consumer;
import com.example.item.model.Item;
import com.example.item.model.ItemWithConsumer;
import com.example.item.service.ConsumerServiceProxy;
import com.example.item.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("item")
public class ItemController implements BasicController <Item>{
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class.getName());

    private ItemService itemService;
    @Autowired
    private final ConsumerServiceProxy consumerServiceProxy;

    public ItemController(ItemService itemService, ConsumerServiceProxy consumerServiceProxy) {

        this.itemService = itemService;
        this.consumerServiceProxy = consumerServiceProxy;
    }

    @Override
    @PostMapping(value = "addItem", produces = "application/json")
    public Item add(@RequestBody Item item) {

        String itemName = item.getName();
        logger.info(new StringBuilder().append("Adding item [").append(itemName).append("].").toString());

        return this.itemService.add(item);
    }

    @Override
    @GetMapping(value = "getItemById/{id}", produces = "application/json")
    public ItemWithConsumer get(@PathVariable("id") Long id) {
        ItemWithConsumer itemWithConsumer = new ItemWithConsumer();
        itemWithConsumer.setItem(this.itemService.get(id));

        Consumer consumer = consumerServiceProxy.getConsumerById(itemWithConsumer.getConsumerId());

        itemWithConsumer.setConsumer(consumer);

        return itemWithConsumer;
    }

    @Override
    @PutMapping(value = "updateItem", produces = "application/json")
    public Item modify(@RequestBody Item item) {

        String itemName = item.getName();
        logger.info(new StringBuilder().append("Updating item [").append(itemName).append("].").toString());

        return this.itemService.modify(item);
    }

    @Override
    @DeleteMapping(value = "deleteItem", produces = "application/json")
    public boolean delete(@RequestBody Item item) {

        return this.itemService.delete(item);
    }

    @Override
    @GetMapping(value = "getAllItems", produces = "application/json")
    public List<Item> getAll() {

        return this.itemService.getAll();

    }

    @GetMapping(value = "getAllItemsByConsumer/{consumerId}", produces = "application/json")
    public List<Item> getAllItemsForConsumer(@PathVariable("consumerId") Long consumerId) {

        return this.itemService.getAllItemsForConsumer(consumerId);
    }

    @GetMapping(value = "getItemByName/{name}", produces = "application/json")
    public List<Item> getItemByName(@PathVariable("name") String name) {

        return this.itemService.getItemByName(name);
    }
}
