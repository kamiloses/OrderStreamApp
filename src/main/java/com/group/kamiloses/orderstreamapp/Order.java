package com.group.kamiloses.orderstreamapp;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Order {
    private String id;
    private String customerId;
    private LocalDateTime orderDate;

    private LocalDateTime shippedOrder;
    private String status;

}
