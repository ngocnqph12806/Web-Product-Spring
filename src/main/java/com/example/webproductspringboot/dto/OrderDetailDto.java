package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.ReturnDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private String id;
    private String idOrder;
    @NotNull
    @NotEmpty
    private String idProduct;
    private String nameProduct;
    @NotNull
    @NotEmpty
    private Long price;
    @NotNull
    @NotEmpty
    private Long priceSale;
    @NotNull
    @NotEmpty
    private Integer quantity;
    private List<ReturnDetailDto> returns;

}
