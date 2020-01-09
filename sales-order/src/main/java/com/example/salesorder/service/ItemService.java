package com.example.salesorder.service;

import com.example.salesorder.models.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemServiceProxy itemServiceProxy;

    public ItemService(ItemServiceProxy itemServiceProxy){
        this.itemServiceProxy = itemServiceProxy;
    }

    @HystrixCommand(fallbackMethod = "defaultItemService")
    public Item get(String item){
        return this.itemServiceProxy.get(item);
    }

    private Item defaultItemService(String item) {
        Item itemModel = new Item();

        itemModel.setId(-1L);
        itemModel.setName(item);

        return itemModel;
    }

}
