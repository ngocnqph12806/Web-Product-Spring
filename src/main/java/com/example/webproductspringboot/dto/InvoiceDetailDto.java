package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailDto {

    private String id;
    private String idInvoice;
    @NotNull
    @NotEmpty
    private String idProduct;
    @NotNull
    @NotEmpty
    private String nameProduct;
    @NotNull
    @NotEmpty
    private Long price;
    @NotNull
    @NotEmpty
    private Integer quantity;

}
