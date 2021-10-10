package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    private String id;
    private String idCreator;
    private String nameCreator;
    @NotNull
    @NotBlank
    private String idChecker;
    private String nameChecker;
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Long totalPrice;
    @NotNull
    @NotEmpty
    private List<InvoiceDetailDto> invoiceDetails;

}
