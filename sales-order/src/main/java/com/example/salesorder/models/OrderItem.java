package com.example.salesorder.models;

import lombok.Data;

import javax.persistence.*;

    @Data
    @Entity(name = "OrderItem")
    @Table(name = "orderitem")
    public class OrderItem {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String itemName;

        private int quantity;

        private Long orderId;

    }
}
