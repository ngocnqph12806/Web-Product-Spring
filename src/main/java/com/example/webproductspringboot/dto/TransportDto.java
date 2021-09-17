package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransportDto {

    private String id;
    private String idOrder;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String description;
    private Boolean status;
    private Date dateCreated;

}
