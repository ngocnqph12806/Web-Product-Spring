package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.CollectionBrandVo;
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
public class BrandDto {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer countProducts;
    private List<CollectionBrandVo> collectionBrands;

}
