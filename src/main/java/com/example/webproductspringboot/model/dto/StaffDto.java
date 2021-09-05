package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {

    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private String address;
    private String avatar;
    private String role;
    private Boolean status;
    private Date created;

}
