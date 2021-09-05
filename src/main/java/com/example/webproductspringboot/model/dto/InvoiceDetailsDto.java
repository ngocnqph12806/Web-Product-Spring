package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDto {

    private String id;
    private InvoiceDto idInvoice;
    private ProductDetailsDto idProductDetails;
    private Long price;
    private Integer quantity;

}
