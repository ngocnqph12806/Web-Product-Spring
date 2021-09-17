package com.example.webproductspringboot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SearchReviewVo {

    private String[] idProduct;
    private String[] nameProduct;
    private String[] idUser;
    private String[] nameUser;
    private String[] point;
    private String[] description;
    private String[] introduce;
    private String[] status;
    private String[] dateCreated;

}
