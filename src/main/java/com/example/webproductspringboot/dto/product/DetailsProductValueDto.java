package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.ProductDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetailsProductValueDto {

    private String idProductDetails;
    private String idValueDetails;
    private String nameValueDetails;
    private String title;
    private String description;
    private String pathBanner;

    public static DetailsProductValueDto toDto(ProductDetailsEntity entity) {
        if(entity == null) return null;
        return DetailsProductValueDto.builder()
                .idProductDetails(entity.getId())
                .idValueDetails(entity.getIdValueDetails().getId())
                .nameValueDetails(entity.getIdValueDetails().getName())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .pathBanner(entity.getPathBanner())
                .build();
    }
}
