package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.BrandDto;

import java.util.List;

public interface IBrandService {

    List<BrandDto> findAll();

    BrandDto save(BrandDto dto);

    BrandDto findById(String id);
}
