package com.example.webproductspringboot.dto.staff;

import com.example.webproductspringboot.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FormUserAdminDto {

    private String idUser;
    @NotNull(message = "Vui lòng nhập họ tên")
    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 5, max =  50, message = "Họ tên từ 5 - 50 ký tự")
    private String fullName;
    @NotNull(message = "Vui lòng chọn ngày sinh")
    private Date dateOfBirth;
    @NotNull(message = "Vui lòng nhập tên đăng nhập")
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 5, max =  32, message = "Tên đăng nhập từ 5 - 32 ký tự")
    private String userName;
    @NotNull(message = "Vui lòng nhập số điện thoại")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(min = 5, max =  15, message = "Só điện thoại không quá 15 ký tự")
    private String phoneNumber;
    @NotNull(message = "Vui lòng nhập địa chỉ email")
    @NotBlank(message = "Địa chỉ email không được để trống")
    @Size(min = 5, max =  32, message = "Địa chỉ email không quá 32 ký tự")
    @Email(message = "Địa chỉ email không đúng")
    private String email;
    @NotNull(message = "Vui lòng nhập địa chỉ")
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(min = 10, max =  100, message = "Đia chỉ không quá 100 ký tự")
    private String address;
    @NotNull(message = "Vui lòng chọn ảnh đại diện")
    @NotBlank(message = "Ảnh đại diện không được để trống")
    @Size(min = 5, max =  200, message = "Tên ảnh đại diện không quá 200 ký tự")
    private String avatar;
    @NotNull(message = "Vui lòng chọn chức vụ")
    private String role;

    public static FormUserAdminDto toDto(UserEntity entity) {
        if (entity == null) return null;
        return FormUserAdminDto.builder()
                .idUser(entity.getId())
                .fullName(entity.getFullName())
                .dateOfBirth(entity.getDateOfBirth())
                .userName(entity.getUsername())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .avatar(entity.getAvatar())
                .role(entity.getRole())
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(this.idUser)
                .fullName(this.fullName)
                .dateOfBirth(this.dateOfBirth)
                .username(this.userName)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .address(this.address)
                .avatar(this.avatar)
                .role(this.role)
                .build();
    }
}
