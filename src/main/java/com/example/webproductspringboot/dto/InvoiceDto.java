package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.InvoiceDetailVo;
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
    private UserDto idCreator;
    private UserDto nameCreator;
    private UserDto idChecker;
    private UserDto nameChecker;
    private String description;
    private boolean status;
    private Date dateCreated;
    private List<InvoiceDetailVo> invoiceDetails;

}
