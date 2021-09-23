package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull
    @NotEmpty
    private String idCreator;
    private String nameCreator;
    @NotNull
    @NotEmpty
    private String idChecker;
    private String nameChecker;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    private List<InvoiceDetailDto> invoiceDetails;

}
