package com.example.webproductspringboot.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ChangeUserDto {

    private String id;
    @NotNull
    @NotBlank
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
    private String address;
    @NotNull
    @NotBlank
    private String avatar;
    @NotNull
    @NotBlank
    private String role;
    private Boolean status;
    private Boolean block;

}
