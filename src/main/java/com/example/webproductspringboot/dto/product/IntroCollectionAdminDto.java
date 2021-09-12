package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.CollectionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IntroCollectionAdminDto {

    private String id;
    private String name;
    private Integer countProducts;

    public static IntroCollectionAdminDto toDto(CollectionEntity entity) {
        if (entity == null) return null;
        return IntroCollectionAdminDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .countProducts(entity.getLstCollectionCategoryEntities() != null
                        ? entity.getLstCollectionCategoryEntities().stream()
                        .mapToInt(e -> e.getIdCategory().getLstProductEntities().size()).sum()
                        : 0)
                .build();
    }

    public CollectionEntity toEntity() {
        return CollectionEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
