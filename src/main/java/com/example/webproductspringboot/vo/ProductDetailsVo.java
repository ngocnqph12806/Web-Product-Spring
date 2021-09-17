package com.example.webproductspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsVo {

    private String id;
    private String idProduct;
    private String nameProduct;
    private String idValue;
    private String nameValue;
    private String title;
    private String description;
    private String pathBanner;

}
