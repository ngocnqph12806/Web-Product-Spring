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
    private Long idUrlCategory;
    private String nameCategory;
    private String pathCategory;
    private String idBrand;
    private String nameBrand;
    private Long price;
    private Long priceSale;
    private Long idUrl;
    private String pathUrl;
    private String description;
    private Integer pointReview;
    private List<PathImageProductDto> pathImageProduct;

    public static IntroProductDto toDto(ProductEntity entity) {
        if (entity == null) return null;
        return IntroProductDto.builder()
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
                .idUrl(entity.getIdUrl())
                .pathUrl(entity.getPathUrl())
                .pointReview((int) entity.getLstReviewProductEntities().stream().mapToDouble(e -> e.getPoint()).average().orElse(0.0))
                .description(entity.getDescription())
                .pathImageProduct(entity.getLstProductImageEntities().stream().map(PathImageProductDto::toDto).collect(Collectors.toList()))
                .build();
    }

}
