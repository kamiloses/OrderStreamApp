package com.group.kamiloses.orderstreamapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeAnOrderDto {
    private List<ProductDto> products;

}
