package com.example.webproductspringboot.vo;

import lombok.Data;

@Data
public class SearchBannerVo {

    private String[] title;
    private String[] pathImage;
    private String[] link;
    private String[] description;
    private String[] status;
    private String[] dateCreated;

}
