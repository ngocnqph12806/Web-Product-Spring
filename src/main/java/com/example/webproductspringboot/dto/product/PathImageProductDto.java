package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.ProductImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PathImageProductDto {

    private String idImageProduct;
    private String pathImage;

    public static PathImageProductDto toDto(ProductImageEntity entity) {
        if(entity == null) return null;
        return PathImageProductDto.builder()
                .idImageProduct(entity.getId())
                .pathImage(entity.getPath())
                .build();
    }
}
