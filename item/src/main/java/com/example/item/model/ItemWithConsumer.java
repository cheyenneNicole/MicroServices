package com.example.item.model;


public class ItemWithConsumer extends Item{

        Consumer consumer;

        public ItemWithConsumer() {
        }

        public ItemWithConsumer( Consumer consumer) {

            this.consumer = consumer;
        }

        public Consumer getConsumer() {
            return consumer;
        }

        public void setItem(Item item) {
            this.setName(item.getName());
            this.setDescription(item.getDescription());
            this.setPrice(item.getPrice());
        }

        public void setConsumer(Consumer consumer) {
            this.consumer = consumer;
        }
    }

