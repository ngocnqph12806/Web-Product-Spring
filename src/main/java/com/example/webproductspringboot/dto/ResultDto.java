package com.example.webproductspringboot.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResultDto<T> {

    private boolean result;
    private String message;
    private T data;

}
