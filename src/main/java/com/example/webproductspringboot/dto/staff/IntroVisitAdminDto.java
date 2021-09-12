package com.example.webproductspringboot.dto.staff;

import com.example.webproductspringboot.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IntroVisitAdminDto {

    private String idUser;
    private String fullName;
    private Date dateOfBirth;
    private String userName;
    private String phoneNumber;
    private String email;
    private String avatar;
    private Boolean status;
    private Boolean block;

    public static IntroVisitAdminDto toDto(UserEntity entity){
        return IntroVisitAdminDto.builder()
                .idUser(entity.getId())
                .fullName(entity.getFullName())
                .dateOfBirth(entity.getDateOfBirth())
                .userName(entity.getUsername())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .avatar(entity.getAvatar())
                .status(entity.getStatus())
                .block(entity.getBlock())
                .build();
    }

}
