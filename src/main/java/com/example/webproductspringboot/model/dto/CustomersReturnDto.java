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
public class CustomersReturnDto {

    private String id;
    private OrderDto idOrder;
    private VisitDto idVisit;
    private StaffDto idStaff;
    private String description;
    private Boolean status;
    private Date created;

}
