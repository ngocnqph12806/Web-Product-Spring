package com.example.webproductspringboot.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoucherApplyVo {

    private String id;
    private String code;
    private Long priceSale;

}
