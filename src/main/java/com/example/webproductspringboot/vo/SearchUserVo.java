package com.example.webproductspringboot.vo;

import lombok.Data;

@Data
public class SearchUserVo {
    private String[] fullName;
    private String[] dateOfBirth;
    private String[] email;
    private String[] phoneNumber;
    private String[] username;
    private String[] address;
    private String[] avatar;
    private String[] role;
    private String[] status;
    private String[] block;
    private String[] dateCreated;
}
