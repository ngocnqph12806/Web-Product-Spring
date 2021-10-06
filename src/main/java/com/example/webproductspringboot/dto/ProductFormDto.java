package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.utils.MapperModelUtils;
import lombok.Data;

@Data
public class ProductFormDto {

    private String id;
    private String name;
    private String idBrand;
    private String idCategory;
    private Long price;
    private Long priceSale;
    private Integer quantity;
    private String color;
    private String categoryDetails;
    private String location;
    private String path;
    private String pathUserManual;
    private String description;
    private Boolean status;
    private String[] images;

    public ProductDto toDto() {
        return (ProductDto) MapperModelUtils.get().toDto(this, ProductDto.class);
    }

}
