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
public class VoucherUseDto {

    private String id;
    private VoucherDto idVoucher;
    private VisitDto idVisit;
    private Boolean status;
    private Date created;

}
