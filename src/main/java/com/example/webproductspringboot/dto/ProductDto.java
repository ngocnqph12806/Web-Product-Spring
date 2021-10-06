package com.example.webproductspringboot.dto;


import com.example.webproductspringboot.vo.ProductDetailsVo;
import com.example.webproductspringboot.vo.ProductImageVo;
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
public class ProductDto {

    private String id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String idBrand;
    private String nameBrand;
    @NotNull
    @NotEmpty
    private String idCategory;
    private String nameCategory;
    private Long idPathCategory;
    private String pathCategory;
    @NotNull
    @NotEmpty
    private Long price;
    @NotNull
    @NotEmpty
    private Long priceSale;
    @NotNull
    @NotEmpty
    private Integer quantity;
    @NotNull
    @NotEmpty
    private String color;
    @NotNull
    @NotEmpty
    private String categoryDetails;
    @NotNull
    @NotEmpty
    private String location;
    @NotNull
    @NotEmpty
    private String path;
    private Long idPath;
    @NotNull
    @NotEmpty
    private String pathUserManual;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Double pointReview;
    private Integer countWishlist;
    private List<ProductDetailsVo> details;
    private List<ProductImageVo> images;
    private List<ReviewDto> reviews;

}
