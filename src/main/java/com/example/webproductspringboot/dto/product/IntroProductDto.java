package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IntroProductDto {

    private String idProduct;
    private String nameProduct;
    private String idCategory;
    private String nameCategory;
    private String pathCategory;
    private String idBrand;
    private String nameBrand;
    private Long price;
    private List<PathImageProductDto> pathImageProduct;

    public static IntroProductDto toDto(ProductEntity entity){
        if(entity==null) return null;
        return IntroProductDto.builder()
                .idProduct(entity.getId())
                .nameProduct(entity.getName())
                .idCategory(entity.getIdCategory().getId())
                .nameCategory(entity.getIdCategory().getName())
                .pathCategory(entity.getIdCategory().getPathUrl())
                .idBrand(entity.getIdBrand().getId())
                .nameBrand(entity.getIdBrand().getName())
                .price(entity.getPrice())
                .pathImageProduct(entity.getLstProductImageEntities().stream().map(PathImageProductDto::toDto).collect(Collectors.toList()))
                .build();
    }

}
