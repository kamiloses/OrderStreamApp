package com.group.kamiloses.orderstreamapp.dto;

import com.group.kamiloses.orderstreamapp.other.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Product name;


}
