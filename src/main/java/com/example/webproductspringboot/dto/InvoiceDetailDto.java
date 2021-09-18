package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailDto {

    private String id;
    private String idInvoice;
    private String idProduct;
    private String nameProduct;
    private Long price;
    private Integer quantity;

}
