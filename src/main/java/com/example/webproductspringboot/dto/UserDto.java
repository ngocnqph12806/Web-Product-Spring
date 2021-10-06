package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    private String address;
    @NotNull
    @NotBlank
    private String avatar;
    @NotNull
    @NotBlank
    private String role;
    private Boolean status;
    private Boolean block;
    private Date dateCreated;
    private Integer countWishlist;
    private List<OrderDto> orders;
    private List<ReturnDto> returns;
    private List<ReviewDto> reviews;

}
