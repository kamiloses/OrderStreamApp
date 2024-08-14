package com.group.kamiloses.orderstreamapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;
    private String customerId;
    private LocalDateTime orderDate;

    private LocalDateTime shippedOrder;
    private String status;

}
