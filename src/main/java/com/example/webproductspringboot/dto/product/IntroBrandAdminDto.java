package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IntroBrandAdminDto {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer countProducts;

    public static IntroBrandAdminDto toDto(BrandEntity entity) {
        if (entity == null) return null;
        return IntroBrandAdminDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .countProducts(entity.getLstCollectionBrandEntities() != null ? entity.getLstCollectionBrandEntities().size() : 0)
                .build();
    }
}
