package com.example.webproductspringboot.vo;


import lombok.Data;

import java.util.Date;

@Data
public class SearchCategoryVo {

    private String[] name;
    private String[] banner;
    private String[] path;
    private String[] description;
    private String[] status;
    private String[] dateCreated;
    private String[] countProducts;

}
