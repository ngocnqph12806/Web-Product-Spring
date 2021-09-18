package com.example.webproductspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDetailDto {

    private String id;
    private String idReturn;
    private String idOrderDetail;
    private Integer quantity;

}
