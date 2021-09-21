package com.example.webproductspringboot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChangeUserDto {

    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String address;
    private String avatar;
    private String role;
    private Boolean status;
    private Boolean block;

}
