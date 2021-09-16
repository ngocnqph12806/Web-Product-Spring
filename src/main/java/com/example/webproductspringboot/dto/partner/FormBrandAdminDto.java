package com.example.webproductspringboot.dto.partner;

import com.example.webproductspringboot.entity.BrandEntity;
import com.example.webproductspringboot.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Optional;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FormBrandAdminDto {

    private String id;
    @NotNull(message = "Vui lòng nhập tên thương hiệu")
    @NotBlank(message = "Tên thương hiệu không được để trống")
    @Size(min = 5, max = 100, message = "Tên thườn hiệu từ 5 đến 100 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ \\-\\.]+$",
            message = "Tên thương hiệu không đúng")
    private String name;
    @NotNull(message = "Vui lòng nhập số điện thoại")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 15, message = "Số điện thoại không được quá 15 ký tự")
    @Pattern(regexp = "[\\+]?[(]?[0-9]{3}[)]?[-\\.]?[0-9]{3}[-\\.]?[0-9]{4,6}",
            message = "Số điện thoại không đúng")
    private String phoneNumber;
    @NotNull(message = "Vui lòng nhập địa chir")
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(min = 10, max = 100, message = "Địa chỉ từ 10 đến 100 ký tự")
    private String address;
    @NotNull(message = "Vui lòng nhập địa chỉ email")
    @NotBlank(message = "Địa chỉ email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b",
            message = "Địa chỉ email không đúng")
    private String email;
    private String description;

    public static FormBrandAdminDto toDto(BrandEntity entity) {
        if (entity == null) return null;
        return FormBrandAdminDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .description(entity.getDescription())
                .build();
    }

    public BrandEntity toEntity() {
        return BrandEntity.builder()
                .id(this.id)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .email(this.email)
                .description(this.description)
                .build();
    }
}
