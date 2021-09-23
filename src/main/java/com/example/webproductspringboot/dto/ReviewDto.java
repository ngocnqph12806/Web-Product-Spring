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
public class ReviewDto {

    private String id;
    @NotNull
    @NotEmpty
    private String idProduct;
    private String nameProduct;
    @NotNull
    @NotEmpty
    private String idUser;
    private String nameUser;
    @NotNull
    @NotEmpty
    private Integer point;
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    @NotEmpty
    private Boolean introduce;
    private Boolean status;
    private Date dateCreated;
    private List<String> images;

}
