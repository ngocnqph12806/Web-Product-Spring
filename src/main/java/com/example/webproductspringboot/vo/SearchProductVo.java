package com.example.webproductspringboot.vo;

import lombok.Data;

@Data
public class SearchProductVo {

    private String[] name;
    private String[] idBrand;
    private String[] nameBrand;
    private String[] idCategory;
    private String[] nameCategory;
    private String[] price;
    private String[] priceSale;
    private String[] quantity;
    private String[] color;
    private String[] categoryDetails;
    private String[] location;
    private String[] path;
    private String[] idPath;
    private String[] pathUserManual;
    private String[] description;
    private String[] status;
    private String[] dateCreated;
    private Integer p;
    private Integer s;
    private String sort;
    private String field;

}
