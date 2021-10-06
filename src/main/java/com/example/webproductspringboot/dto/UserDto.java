package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    @NotNull
    @NotEmpty
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
    private String address;
    @NotNull
    @NotEmpty
    private String avatar;
    @NotNull
    @NotEmpty
    private String role;
    private Boolean status;
    private Boolean block;
    private Date dateCreated;
    private Integer countWishlist;
    private List<OrderDto> orders;
    private List<ReturnDto> returns;
    private List<ReviewDto> reviews;

}
