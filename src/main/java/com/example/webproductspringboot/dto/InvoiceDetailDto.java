package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String idProduct;
    private String nameProduct;
    @NotNull
    private Long price;
    @NotNull
    private Integer quantity;

}
