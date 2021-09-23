package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.CollectionCategoryVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String banner;
    @NotNull
    @NotEmpty
    private String path;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer countProducts;
    private List<ProductDto> products;
    private List<CollectionCategoryVo> collectionCategories;

}
