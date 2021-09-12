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
public class SearchCategoryDto {

    private String idCategory;
    private String name;
    private Long idUrl;
    private String pathUrl;
    private String banner;

    public static SearchCategoryDto toDto(CategoryEntity entity){
        if(entity==null) return null;
        return SearchCategoryDto.builder()
                .idCategory(entity.getId())
                .name(entity.getName())
                .idUrl(entity.getIdUrl())
                .pathUrl(entity.getPathUrl())
                .banner(entity.getBanner())
                .build();
    }

}
