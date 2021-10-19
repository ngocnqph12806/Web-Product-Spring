package com.example.webproductspringboot.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class WishlistVo {

    private String id;
    @NotNull(message = "wishlist.not.null.id.product")
    @NotBlank(message = "wishlist.not.blank.id.product")
    private String idProduct;
    private String idVisit;

}
