package com.example.webproductspringboot.dto;


import com.example.webproductspringboot.vo.ProductDetailsVo;
import com.example.webproductspringboot.vo.ProductImageVo;
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
public class ProductDto {

    private String id;
    private String name;
    private String idBrand;
    private String nameBrand;
    private String idCategory;
    private String nameCategory;
    private Long price;
    private Long priceSale;
    private Integer quantity;
    private String color;
    private String categoryDetails;
    private String location;
    private String path;
    private Long idPath;
    private String pathUserManual;
    private String description;
    private Boolean status;
    private Date dateCreated;
    private List<ProductDetailsVo> details;
    private List<ProductImageVo> images;
    private List<ReviewDto> reviews;

}
