package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.product.IntroBrandAdminDto;

import java.util.List;

public interface IBrandService  {
    List<IntroBrandAdminDto> findAllIntroBrandAdmin();
}
