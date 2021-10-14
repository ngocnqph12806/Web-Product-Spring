package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotNull
    @NotBlank(message = "fullName.not.blank")
    @Size(min = 5, max = 32, message = "fullName.form.5.to.32.character")
    private String fullName;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String registerPassword;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    private String avatar;

}
