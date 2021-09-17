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
public class OrderDto {

    private String id;
    private String idUser;
    private String nameUser;
    private String idCreator;
    private String nameCreator;
    private String idSaller;
    private String nameSaller;
    private String paymentMethod;
    private Boolean paymentStatus;
    private String description;
    private Boolean status;
    private Date dateCreated;
    private List<OrderDetailDto> details;
    private List<TransportDto> transports;


}
