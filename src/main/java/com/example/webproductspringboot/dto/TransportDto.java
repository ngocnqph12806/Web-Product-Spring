package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransportDto {

    private String id;
    @NotNull
    @NotBlank
    private String idOrder;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String description;
    private String statusTransport;
    private Date dateCreated;

}
