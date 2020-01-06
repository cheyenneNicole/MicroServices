package com.example.consumer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first_name;
    private String last_name;
    private String email;

    @ElementCollection
    private List<Long> itemIds = new ArrayList<>();

    protected Consumer() {

    }

    public Consumer(String first_name, String last_name, String email) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // This is needed to update the relationship between Item and Consumer when adding a Item
    public void addItem(Long itemId) {
        itemIds.add(itemId);
    }

    // This is needed to update the relationship between Item and Consumer when removing a Item
    public void removeItem(Long itemId) {
        itemIds.remove(itemId);
    }

    public List<Long> getItemIds() {
        return this.itemIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return id == consumer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Consumer {");
        sb.append("id=").append(id);
        sb.append(", first_name='").append(first_name).append('\'');
        sb.append(", last_name='").append(last_name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');

        return sb.toString();
    }

    // Builder pattern using static builder
    public static ConsumerBuilder builder() {
        return new ConsumerBuilder();
    }

    public static final class ConsumerBuilder {

        private Consumer consumer;

        private ConsumerBuilder() {
            consumer = new Consumer();
        }

        public ConsumerBuilder withId(Long id) {
            consumer.setId(id);
            return this;
        }

        public ConsumerBuilder withFirstName(String first_name) {
            consumer.setFirst_name(first_name);
            return this;
        }

        public ConsumerBuilder withLastName(String last_name) {
            consumer.setLast_name(last_name);
            return this;
        }

        public ConsumerBuilder withEmail(String email) {
            consumer.setEmail(email);
            return this;
        }


        public ConsumerBuilder withItem(Long itemId) {
            consumer.addItem(itemId);
            return this;
        }

        public Consumer build() {
            return consumer;
        }
    }
}
