package com.example.salesorder.models;


import javax.persistence.*;
import java.util.Objects;

@Entity(name="SalesOrder")
public class SalesOrder{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;


    private Long email_id;


    protected SalesOrder(){

    }

    public SalesOrder(String name, String description){
        this.name = name;
        this.description = description;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setEmail_id(Long email_id){
        this.email_id = email_id;
    }
    public Long getEmail_id(){
        return email_id;
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
        sb.append(", name=").append(name);
        sb.append(", description='").append(description).append('\'');
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

        public SalesOrderBuilder withName(String name) {
            salesOrder.setName(name);
            return this;
        }

        public SalesOrderBuilder withDescription(String description) {
            salesOrder.setDescription(description);
            return this;
        }

        public SalesOrderBuilder withEmail_id(Long email_id) {
            salesOrder.setEmail_id(email_id);
            return this;
        }

        public SalesOrder build() {
            return salesOrder;
        }
    }
}

