package com.example.webproductspringboot.vo;

import com.example.webproductspringboot.dto.UserDto;
import lombok.Data;

import java.util.Date;

@Data
public class SearchInvoiceVo {

    private String[] idCreator;
    private String[] nameCreator;
    private String[] idChecker;
    private String[] nameChecker;
    private String[] description;
    private String[] status;
    private String[] dateCreated;

}
