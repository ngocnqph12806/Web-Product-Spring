package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

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
    @NotNull(message = "invoice.not.null.price")
    @Min(value = 1, message = "invoice.price.min.1")
    @Max(value = 99999000, message = "invoice.price.max.99999000")
    private Long price;
    @NotNull(message = "invoice.not.null.quantity")
    @Min(value = 1, message = "invoice.quantity.min.1")
    @Max(value = 99999, message = "invoice.quantity.max.99999")
    private Integer quantity;

}
