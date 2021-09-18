package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.BrandDto;

import java.util.List;

public interface IBrandService {

    List<BrandDto> findAll();

    BrandDto findById(String id);

    BrandDto save(BrandDto dto);
}
