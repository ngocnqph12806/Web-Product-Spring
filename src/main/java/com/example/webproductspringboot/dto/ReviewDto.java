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
public class ReviewDto {

    private String id;
    private String idProduct;
    private String nameProduct;
    private String idUser;
    private String nameUser;
    private Integer point;
    private String description;
    private Boolean introduce;
    private Boolean status;
    private Date dateCreated;
    private List<String> images;

}
