package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransportDto {

    private String id;
    @NotNull
    @NotEmpty
    private String idOrder;
    @NotNull
    @NotEmpty
    private String fullName;
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @NotNull
    @NotEmpty
    private String address;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;

}
