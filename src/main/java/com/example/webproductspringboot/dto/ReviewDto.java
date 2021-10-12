package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private String id;
    @NotNull
    @NotBlank
    private String idProduct;
    private String nameProduct;
//    @NotNull
//    @NotEmpty
    private String idUser;
    private String nameUser;
    @NotNull
    @Min(20)
    @Max(100)
    private Integer point;
    @NotNull
    @NotBlank
    private String description;
//    @NotNull
//    @NotEmpty
    private Boolean introduce;
    private Boolean status;
    private Date dateCreated;
    private List<String> images;

}
