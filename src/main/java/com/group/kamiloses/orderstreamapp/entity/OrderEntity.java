package com.group.kamiloses.orderstreamapp.entity;

import com.group.kamiloses.orderstreamapp.other.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    private String id;
    private String userId;
    private List<ProductEntity> productEntities;
    private Date orderDate;
    private Date shippedOrder;
    private Status status;


}
