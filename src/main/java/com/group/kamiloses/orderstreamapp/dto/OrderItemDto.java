package com.group.kamiloses.orderstreamapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private String productId;
    private int quantity;
    private double price;
}
