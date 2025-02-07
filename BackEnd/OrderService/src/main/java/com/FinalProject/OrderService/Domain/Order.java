package com.FinalProject.OrderService.Domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
        @Id
        private int orderId;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public Order(int orderId) {
            this.orderId = orderId;
        }

        public Order() {
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderId=" + orderId +
                    '}';
        }
    }


