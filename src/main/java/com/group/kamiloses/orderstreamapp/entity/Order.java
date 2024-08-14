package com.group.kamiloses.orderstreamapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private String customerId;
    private LocalDateTime orderDate;

    private LocalDateTime shippedOrder;
    private String status;

}
