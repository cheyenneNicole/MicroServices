package com.example.restclient.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Item {

    private Long id;

    private String name;
    private String description;
    private Double price;

    // Lazy fetch is better for performance than eager
    private Long consumerId;


    protected Item() {

    }

    public Item(String name, String description, Double price) {

        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description=").append(description);
        sb.append(" , price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static final class ItemBuilder {
        private Item item;

        private ItemBuilder() {
            item = new Item();
        }

        public ItemBuilder withId(Long id) {
            item.setId(id);
            return this;
        }

        public ItemBuilder withName(String name) {
            item.setName(name);
            return this;
        }

        public ItemBuilder withDescription(String description) {
            item.setDescription(description);
            return this;
        }

        public ItemBuilder withConsumerId(Long consumerId) {
            item.setConsumerId(consumerId);
            return this;
        }


        public Item build() {
            return item;
        }
    }
}

