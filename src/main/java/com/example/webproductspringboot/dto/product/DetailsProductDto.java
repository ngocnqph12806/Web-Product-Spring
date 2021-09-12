package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetailsProductDto {

    private String idProduct;
    private String nameProduct;
    private String idCategory;
    private Long idUrlCategory;
    private String nameCategory;
    private String pathCategory;
    private String idBrand;
    private String nameBrand;
    private Long price;
    private Long priceSale;
    private String pathUserManual;
    private String description;
    private Integer pointReview;
    private List<String> lstPathImages;
    private List<DetailsProductValueDto> detailsProduct;
    private List<ReviewDto> reviewProduct;
    private Set<String> lstIdDetailsProduct;

    public static DetailsProductDto toDto(ProductEntity entity) {
        if (entity == null) return null;
        return DetailsProductDto.builder()
                .idProduct(entity.getId())
                .nameProduct(entity.getName())
                .idCategory(entity.getIdCategory().getId())
                .idUrlCategory(entity.getIdCategory().getIdUrl())
                .nameCategory(entity.getIdCategory().getName())
                .pathCategory(entity.getIdCategory().getPathUrl())
                .idBrand(entity.getIdBrand().getId())
                .nameBrand(entity.getIdBrand().getName())
                .price(entity.getPrice())
                .priceSale(entity.getPriceSale())
                .pathUserManual(entity.getPathUserManual())
                .description(entity.getDescription())
                .pointReview((int) entity.getLstReviewProductEntities().stream().mapToDouble(e -> e.getPoint()).average().orElse(0.0))
                .lstPathImages(entity.getLstProductImageEntities().stream().map(e -> e.getPath()).collect(Collectors.toList()))
                .detailsProduct(entity.getLstProductDetailsEntities().stream().map(DetailsProductValueDto::toDto).collect(Collectors.toList()))
                .reviewProduct(entity.getLstReviewProductEntities().stream().map(ReviewDto::toDto).collect(Collectors.toList()))
                .lstIdDetailsProduct(entity.getLstProductDetailsEntities().stream().map(e -> e.getIdValueDetails().getId()).collect(Collectors.toSet()))
                .build();
    }

}
