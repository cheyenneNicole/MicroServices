package com.example.salesorder.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Objects;

@Data
@Entity(name="SalesOrder")
@Table(name = "salesorder")
public class SalesOrder{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Email
    private String email;

    private Double price;

    private Date date;

    public SalesOrder(){

    }

    public SalesOrder(String description, String email, Date date, Double price){
        this.date = date;
        this.description = description;
        this.email = email;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesOrder salesOrder = (SalesOrder) o;
        return Objects.equals(id, salesOrder.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sales Order{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", description='").append(description).append('\'');
        sb.append(", email=").append(email);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    public static SalesOrderBuilder builder() {
        return new SalesOrderBuilder();
    }

    public static final class SalesOrderBuilder {

        private SalesOrder salesOrder;

        private SalesOrderBuilder() {
            salesOrder = new SalesOrder();
        }

        public SalesOrderBuilder withId(Long id) {
            salesOrder.setId(id);
            return this;
        }
        public SalesOrderBuilder withDate(Date date) {
            salesOrder.setDate(date);
            return this;
        }
        public SalesOrderBuilder withDescription(String description) {
            salesOrder.setDescription(description);
            return this;
        }

        public SalesOrderBuilder withPrice(double price) {
            salesOrder.setPrice(price);
            return this;
        }

        public SalesOrder build() {
            return salesOrder;
        }
    }
}

