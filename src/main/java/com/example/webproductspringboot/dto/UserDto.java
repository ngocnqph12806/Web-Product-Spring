package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String username;
    private String address;
    private String avatar;
    private String role;
    private Boolean status;
    private Boolean block;
    private Date dateCreated;
    private List<OrderDto> orders;
    private List<ReturnDto> returns;
    private List<ReviewDto> reviews;

}
