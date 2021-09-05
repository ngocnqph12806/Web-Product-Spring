package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    private String id;
    private StaffDto idStaffCreate;
    private StaffDto idStaffCheck;
    private String description;
    private Boolean status;
    private Date created;

}
