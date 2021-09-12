package com.example.webproductspringboot.dto;

import lombok.*;

@Data
@ToString
public class ResultDto<T> {

    private String message;
    private boolean result;
    private T data;

}
