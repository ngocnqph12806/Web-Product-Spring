package com.example.webproductspringboot.vo;

import lombok.Data;

@Data
public class SearchHistoryVo {

    private String[] idStaff;
    private String[] nameStaff;
    private String[] description;
    private String[] dateCreated;

}
