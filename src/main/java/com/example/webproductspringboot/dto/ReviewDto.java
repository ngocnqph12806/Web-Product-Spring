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
    @NotNull(message = "review.not.null.id.product")
    @NotBlank(message = "review.not.blank.id.product")
    private String idProduct;
    private String nameProduct;
    @NotNull(message = "review.not.null.id.user")
    @NotBlank(message = "review.not.blank.id.user")
    private String idUser;
    private String nameUser;
    @NotNull(message = "review.not.null.point")
    @Min(20)
    @Max(100)
    private Integer point;
    @NotNull(message = "review.not.null.description")
    @NotBlank(message = "review.not.blank.description")
    private String description;
//    @NotNull
//    @NotEmpty
    private Boolean introduce;
    private Boolean status;
    private Date dateCreated;
    private List<String> images;

}
