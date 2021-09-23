package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.CollectionBrandVo;
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
public class BrandDto {

    private String id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String address;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer countProducts;
    private List<CollectionBrandVo> collectionBrands;

}
