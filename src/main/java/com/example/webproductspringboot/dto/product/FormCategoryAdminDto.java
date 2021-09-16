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
public class FormCategoryAdminDto {

    private String id;
    private String name;
    private String banner;
    private String pathUrl;
    private String description;

    public static FormCategoryAdminDto toDto(CategoryEntity entity) {
        if (entity == null) return null;
        return FormCategoryAdminDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .banner(entity.getBanner())
                .pathUrl(entity.getPathUrl())
                .description(entity.getDescription())
                .build();
    }

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .id(this.id)
                .name(this.name)
                .banner(this.banner)
                .pathUrl(this.pathUrl)
                .description(this.description)
                .build();
    }
}
