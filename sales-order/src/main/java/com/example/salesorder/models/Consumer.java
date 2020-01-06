package com.example.salesorder.models;

import lombok.Data;
import java.util.Objects;

import java.util.Objects;

@Data
public class Consumer {

        private Long id;

        private String firstName;
        private String lastName;

        private String email;

        public Consumer() {

        }

        public Consumer(String firstName, String lastName, String email) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
            sb.append(", firstName='").append(firstName).append('\'');
            sb.append(", lastName=").append(lastName);
            sb.append(", email=").append(email);
            sb.append('}');
            return sb.toString();
        }

        public static CustomerBuilder builder() {
            return new CustomerBuilder();
        }

        public static final class CustomerBuilder {
            private Consumer consumer;

            private CustomerBuilder() {
                consumer = new Consumer();
            }

            public CustomerBuilder withId(Long id) {
                consumer.setId(id);
                return this;
            }

            public CustomerBuilder withFirstName(String firstName) {
                consumer.setFirstName(firstName);
                return this;
            }

            public CustomerBuilder withLastName(String lastName) {
                consumer.setLastName(lastName);
                return this;
            }

            public CustomerBuilder withEmail(String Email) {
                consumer.setEmail(Email);
                return this;
            }

            public Consumer build() {
                return consumer;
            }
        }
}
