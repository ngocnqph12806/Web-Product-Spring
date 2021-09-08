package com.example.webproductspringboot.dto;

import lombok.Data;

@Data
public class RequestCommand <T> {

    private Boolean rememberPassword;

    private T data;

}
