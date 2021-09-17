package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.ReturnDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private String id;
    private String idProduct;
    private String nameProduct;
    private Long price;
    private Long priceSale;
    private Integer quantity;
    private List<ReturnDetailDto> returns;

}
