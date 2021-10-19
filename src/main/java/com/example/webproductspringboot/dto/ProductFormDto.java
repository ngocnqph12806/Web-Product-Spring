package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.utils.MapperModelUtils;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProductFormDto {

    private String id;
    @NotNull(message = "product.form.not.null.name")
    @NotBlank(message = "product.form.not.blank.name")
//    @Pattern(regexp = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ -.]{5,10}$",
//            message = "product.form.pattern.name")
    private String name;
    @NotNull(message = "product.form.not.null.id.brand")
    @NotBlank(message = "product.form.not.blank.id.brand")
    private String idBrand;
    @NotNull(message = "product.form.not.null.id.category")
    @NotBlank(message = "product.form.not.blank.id.category")
    private String idCategory;
    @NotNull(message = "product.form.not.null.price")
    @Min(value = 1, message = "product.form.price.min.1")
    @Max(value = 99999000, message = "product.form.price.max.99999000")
    private Long price;
    @NotNull(message = "product.form.not.null.price.sale")
    @Min(value = 0, message = "product.form.price.sale.min.0")
    @Max(value = 9990000,
            message = "product.form.price.sale.max.9990000")
    private Long priceSale;
//    @NotNull(message = "product.form.not.null.quantity")
//    @Min(value = 1, message = "product.form.quantity.min.1")
//    @Max(value = 99999, message = "product.form.quantity.max.99999")
    private Integer quantity;
    @NotNull(message = "product.form.not.null.color")
    @NotBlank(message = "product.form.not.blank.color")
//    @Pattern(regexp = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ -.]{2,30}$",
//            message = "product.form.pattern.color")
    private String color;
    @NotNull(message = "product.form.not.null.category.detail")
    @NotBlank(message = "product.form.not.blank.category.detail")
//    @Pattern(regexp = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ -.]+$",
//            message = "product.form.pattern.category.detail")
    private String categoryDetails;
    @NotNull(message = "product.form.not.null.location")
    @NotBlank(message = "product.form.not.blank.location")
    private String location;
    @NotNull(message = "product.form.not.null.path")
    @NotBlank(message = "product.form.not.blank.path")
    private String path;
    @NotNull(message = "product.form.not.null.pathUserManual")
    @NotBlank(message = "product.form.not.blank.pathUserManual")
//    @Pattern(regexp = "^.*.(docx|DOCX|xlsx|xlsm|xls|xml|doc|DOC|pdf|PDF)$",
//            message = "product.form.pattern.path.user.manual")
    private String pathUserManual;
    @NotNull(message = "product.form.not.null.description")
    @NotBlank(message = "product.form.not.blank.description")
    @Size(min = 255, message = "product.form.size.description.min.255")
    private String description;
    private Boolean status;
//    @NotNull(message = "product.form.not.null.images")
//    @NotEmpty(message = "product.form.not.blank.images")
    private String[] images;

    public ProductDto toDto() {
        return (ProductDto) MapperModelUtils.get().toDto(this, ProductDto.class);
    }

}
