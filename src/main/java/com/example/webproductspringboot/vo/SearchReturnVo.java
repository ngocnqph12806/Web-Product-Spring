package com.example.webproductspringboot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SearchReturnVo {

    private String[] idOrder;
    private String[] dateOrder;
    private String[] idUser;
    private String[] nameUser;
    private String[] idStaff;
    private String[] nameStaff;
    private String[] description;
    private String[] status;
    private String[] dateCreated;

}
