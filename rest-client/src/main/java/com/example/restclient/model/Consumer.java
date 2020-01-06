package com.example.restclient.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Consumer {

    private Long id;

    private String firstname;
    private String lastname;
    private String email;


    private List<Long> itemIds = new ArrayList<>();

    protected Consumer() {

    }

    public Consumer(String firstname, String lastname, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String fname) {
        this.firstname = fname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lname) {
        this.lastname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }


    public void addItem(Long itemId) {
        itemIds.add(itemId);
    }

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
        Consumer customer = (Consumer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Customer {");
        sb.append("id=").append(id);
        sb.append(", First name='").append(firstname).append('\'');
        sb.append(", Last name='").append(lastname).append('\'');
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

        public ConsumerBuilder withFirstName(String fname) {
            consumer.setFirstName(fname);
            return this;
        }

        public ConsumerBuilder withLastName(String lname) {
            consumer.setLastName(lname);
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
