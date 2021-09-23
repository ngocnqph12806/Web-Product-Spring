package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDto {

    private String id;
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String idStaff;
    private String nameStaff;
    @NotNull
    @NotEmpty
    private Integer quantity;
    @NotNull
    @NotEmpty
    private Long priceSale;
    @NotNull
    @NotEmpty
    private Date dateStart;
    @NotNull
    @NotEmpty
    private Date dateEnd;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer quantityUsed;

}
