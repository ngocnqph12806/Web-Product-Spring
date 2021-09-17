package com.example.webproductspringboot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SearchOrderVo {

    private String[] idUser;
    private String[] nameUser;
    private String[] idCreator;
    private String[] nameCreator;
    private String[] idSaller;
    private String[] nameSaller;
    private String[] paymentMethod;
    private String[] paymentStatus;
    private String[] description;
    private String[] status;
    private String[] dateCreated;

}
