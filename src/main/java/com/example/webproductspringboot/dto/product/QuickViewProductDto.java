package com.example.webproductspringboot.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QuickViewProductDto {

    private String idProduct;
    private String nameProduct;
    private String idCollection;
    private String nameCollection;
    private Long price;
    private List<String> pathImageProducts;
    private List<DetailsProductDto> detailsProducts;

}
