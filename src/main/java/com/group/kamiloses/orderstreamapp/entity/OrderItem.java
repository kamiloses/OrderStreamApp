package com.group.kamiloses.orderstreamapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    private String productId;
    private int quantity;
    private double price;
}
