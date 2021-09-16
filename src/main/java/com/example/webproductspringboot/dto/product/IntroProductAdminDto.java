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
public class IntroProductAdminDto {

    private String id;
    private String nameProduct;
    private String nameCategory;
    private String nameBrand;
    private Long price;
    private Long priceSale;
    private Integer pointReview;
    private Boolean status;

    public static IntroProductAdminDto toDto(ProductEntity entity) {
        if (entity == null) return null;
        return IntroProductAdminDto.builder()
                .id(entity.getId())
                .nameProduct(entity.getName())
                .nameCategory(entity.getIdCategory() != null ? entity.getIdCategory().getName() : "")
                .nameBrand(entity.getIdBrand() != null ? entity.getIdBrand().getName() : "")
                .price(entity.getPrice())
                .priceSale(entity.getPriceSale())
                .pointReview(entity.getLstReviewProductEntities() != null
                        ? (int) entity.getLstReviewProductEntities().stream().mapToDouble(e -> e.getPoint()).average().orElse(0.0)
                        : 0)
                .status(entity.getStatus())
                .build();
    }

}
