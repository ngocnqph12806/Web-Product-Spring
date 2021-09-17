package com.example.webproductspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollectionCategoryVo {

    private String id;
    private String idCategory;
    private String nameCategory;
    private String idCollection;
    private String nameCollection;

}
