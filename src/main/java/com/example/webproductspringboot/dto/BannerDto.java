package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto {

    private String id;
    private String title;
    private String pathImage;
    private String link;
    private String description;
    private Boolean status;
    private Date dateCreated;

}
