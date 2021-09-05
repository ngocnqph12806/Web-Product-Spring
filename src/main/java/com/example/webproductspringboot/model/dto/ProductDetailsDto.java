package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {

    private String id;
    private ProductDto idProduct;
    private AttributeValueDto idAttributeValue;
    private Long price;
    private Integer quantity;
    private String location;

}
