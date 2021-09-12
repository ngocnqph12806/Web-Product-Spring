package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IntroCategoryAdminDto {

    private String id;
    private String name;
    private String pathCategory;
    private Integer countProducts;

    public static IntroCategoryAdminDto toDto(CategoryEntity entity) {
        if (entity == null) return null;
        return IntroCategoryAdminDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pathCategory(entity.getPathUrl())
                .countProducts(entity.getLstProductEntities() != null ? entity.getLstProductEntities().size() : 0)
                .build();
    }
}
