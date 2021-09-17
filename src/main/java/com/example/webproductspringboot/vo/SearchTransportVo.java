package com.example.webproductspringboot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SearchTransportVo {

    private String[] idOrder;
    private String[] fullName;
    private String[] phoneNumber;
    private String[] address;
    private String[] description;
    private String[] status;
    private String[] dateCreated;

}
