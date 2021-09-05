package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomersReturnDetailsDto {

    private String id;
    private CustomersReturnDto idCustomersReturn;
    private OrderDtailsDto idOrderDtails;
    private Integer quantity;



}
