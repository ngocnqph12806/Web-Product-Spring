package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String idChecker;
    private String nameChecker;
    private String description;
    private Boolean status;
    private Date dateCreated;
    private List<InvoiceDetailDto> invoiceDetails;

}
