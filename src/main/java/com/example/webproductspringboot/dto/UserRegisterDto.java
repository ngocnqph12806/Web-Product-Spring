package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotNull(message = "user.not.null.full.name")
    @NotBlank(message = "user.not.blank.full.name")
//    @Pattern(regexp = "[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{5,32}$",
//            message = "user.pattern.full.name")
    private String fullName;
    @NotNull(message = "user.not.null.date.of.birth")
    private Date dateOfBirth;
    @NotNull(message = "user.not.null.email")
    @NotBlank(message = "user.not.blank.email")
//    @Pattern(regexp = "^(([^<>()[\\]\\.,;:\\s@\"]+(\\.[^<>()[\\]\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
//            message = "user.pattern.email")
    private String email;
    @NotNull(message = "user.not.null.phone.number")
    @NotBlank(message = "user.not.blank.phone.number")
//    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
//            message = "user.pattern.phone.number")
    private String phoneNumber;
    @NotNull(message = "user.not.null.username")
    @NotBlank(message = "user.not.blank.username")
//    @Pattern(regexp = "[a-zA-Z0-9-]{5,32}$",
//            message = "user.pattern.username")
    private String username;
    @NotNull(message = "user.not.null.password")
    @NotBlank(message = "user.not.blank.password")
    @Min(value = 8, message = "user.password.min.8")
    private String password;
    @NotNull(message = "user.not.null.re.password")
    @NotBlank(message = "user.not.blank.re.password")
    @Min(value = 8, message = "user.re.password.min.8")
    private String registerPassword;
    @NotNull(message = "user.not.null.address")
    @NotBlank(message = "user.not.blank.address")
    private String address;
    @NotNull(message = "user.not.null.avatar")
    @NotBlank(message = "user.not.blank.avatar")
//    @Pattern(regexp = ".*.(gif|jpe?g|bmp|png)",
//            message = "user.pattern.avatar")
    private String avatar;
    @NotNull(message = "user.not.null.url.client")
    @NotBlank(message = "user.not.blank.url.client")
    private String urlClient;

}
