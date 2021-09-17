package com.example.webproductspringboot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SearchCollectionVo {

    private String[] name;
    private String[] description;
    private String[] status;
    private String[] dateCreated;
    private String[] countProducts;

}
