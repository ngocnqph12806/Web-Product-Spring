package com.example.webproductspringboot.vo;

import lombok.Data;


@Data
public class SearchVoucherVo {

    private String[] code;
    private String[] title;
    private String[] idStaff;
    private String[] nameStaff;
    private String[] quantity;
    private String[] priceSale;
    private String[] dateStart;
    private String[] dateEnd;
    private String[] description;
    private String[] status;
    private String[] dateCreated;

}
