package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.CollectionBrandVo;
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
public class BrandDto {

    private String id;
    @NotNull(message = "brand.not.null.name")
    @NotBlank(message = "brand.not.blank.name")
//    @Pattern(regexp = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ -.]{5,10}$",
//    message="brand.pattern.name")
    private String name;
    @NotNull(message = "brand.not.null.phone.number")
    @NotBlank(message = "brand.not.blank.phone.number")
//    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
//            message="brand.pattern.phone.number")
    private String phoneNumber;
    @NotNull(message = "brand.not.null.email")
    @NotBlank(message = "brand.not.blank.email")
//    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$",
//            message="brand.pattern.email")
    private String email;
    @NotNull(message = "brand.not.null.address")
    @NotBlank(message = "brand.not.blank.address")
    private String address;
    @NotNull(message = "brand.not.null.description")
    @NotBlank(message = "brand.not.blank.description")
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer countProducts;
    private List<CollectionBrandVo> collectionBrands;

}
