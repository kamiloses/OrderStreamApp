package com.group.kamiloses.orderstreamapp.entity;

import com.group.kamiloses.orderstreamapp.other.Product;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private String id;
    private Product name;


}
