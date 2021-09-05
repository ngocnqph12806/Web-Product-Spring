package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtailsDto {

    private String id;
    private OrderDto idOrder;
    private ProductDetailsDto idProductDetails;
    private Long price;
    private Integer quantity;
    private Long priceSale;

}
