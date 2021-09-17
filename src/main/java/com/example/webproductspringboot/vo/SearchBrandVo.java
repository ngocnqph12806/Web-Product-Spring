package com.example.webproductspringboot.vo;

import lombok.Data;

@Data
public class SearchBrandVo {

    private String[] id;
    private String[] name;
    private String[] phoneNumber;
    private String[] email;
    private String[] address;
    private String[] description;
    private String[] status;
    private String[] dateCreated;
    private String[] countProducts;

}
