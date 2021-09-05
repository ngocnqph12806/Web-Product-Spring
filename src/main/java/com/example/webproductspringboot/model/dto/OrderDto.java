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
public class OrderDto {

    private String id;
    private VisitDto idVisit;
    private StaffDto staffCreate;
    private StaffDto staffSales;
    private String paymentMethod;
    private Boolean paymentStatus;
    private String description;
    private Boolean status;
    private Date created;

}
