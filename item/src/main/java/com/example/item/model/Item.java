package com.example.item.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Double price;

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
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        sb.append(", price=").append(price);
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


        public ItemBuilder withPrice(Double price) {
            item.setPrice(price);
            return this;
        }

        public Item build() {
            return item;
        }
    }
}