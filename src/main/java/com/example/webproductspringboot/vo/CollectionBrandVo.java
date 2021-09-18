package com.example.webproductspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollectionBrandVo {

    private String id;
    private String idBrand;
    private String nameBrand;
    private String idCollection;
    private String nameCollection;

}
