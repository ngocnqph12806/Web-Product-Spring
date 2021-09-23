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
public class OrderDto {

    private String id;
    @NotNull
    @NotEmpty
    private String idUser;
    private String nameUser;
    @NotNull
    @NotEmpty
    private String idCreator;
    private String nameCreator;
    @NotNull
    @NotEmpty
    private String idSaller;
    private String nameSaller;
    @NotNull
    @NotEmpty
    private String paymentMethod;
    @NotNull
    @NotEmpty
    private Boolean paymentStatus;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    private List<OrderDetailDto> details;
    private List<TransportDto> transports;


}
