package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.CollectionCategoryVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String id;
    @NotNull(message = "category.not.null.name")
    @NotBlank(message = "category.not.blank.name")
//    @Pattern(regexp = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ -.]{5,10}$",
//            message = "category.pattern.name")
    private String name;
    @NotNull(message = "category.not.null.banner")
    @NotBlank(message = "category.not.blank.banner")
//    @Pattern(regexp = ".*.(gif|jpe?g|bmp|png)",
//            message = "category.pattern.banner")
    private String banner;
    @NotNull(message = "category.not.null.path")
    @NotBlank(message = "category.not.blank.path")
    private String path;
    private Long idPath;
    @NotNull(message = "category.not.null.description")
    @NotBlank(message = "category.not.blank.description")
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer countProducts;
    private List<ProductDto> products;
    private List<CollectionCategoryVo> collectionCategories;

}
