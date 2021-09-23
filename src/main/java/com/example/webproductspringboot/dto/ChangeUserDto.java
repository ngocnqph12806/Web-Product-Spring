package com.example.webproductspringboot.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ChangeUserDto {

    private String id;
    private String fullName;
    @NotNull
    @NotEmpty
    private Date dateOfBirth;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String address;
    @NotNull
    @NotEmpty
    private String avatar;
    @NotNull
    @NotEmpty
    private String role;
    private Boolean status;
    private Boolean block;

}
