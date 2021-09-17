package com.example.webproductspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageVo {

    private String id;
    private String path;
    private String idProduct;
    private String nameProduct;

}
