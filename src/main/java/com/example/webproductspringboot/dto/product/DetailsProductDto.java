package com.example.webproductspringboot.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetailsProductDto {

    private String idDetailsProduct;
    private String nameAttribute;
    private String valueAttribute;
    private Long price;
    private Integer quantity;
    private String location;

}
