package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InfoStaffDto {

    private String idStaff;
    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String avatar;
    private String role;
    private Boolean status;

    public static InfoStaffDto toDto(StaffEntity entity){
        return InfoStaffDto.builder()
                .idStaff(entity.getId())
                .fullName(entity.getFullName())
                .dateOfBirth(entity.getDateOfBirth())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .avatar(entity.getAvatar())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

}
