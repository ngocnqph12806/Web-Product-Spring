package com.example.webproductspringboot.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class WishlistVo {

    private String id;
    @NotNull
    @NotBlank
    private String idProduct;
    private String idVisit;

}
