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
public class VoucherDto {

    private String id;
    private String code;
    private String title;
    private String idStaff;
    private String nameStaff;
    private Integer quantity;
    private Long priceSale;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private Boolean status;
    private Date dateCreated;

}
