package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.PageDto;

import java.util.List;

public interface IBrandService {

    List<BrandDto> findAll();

    PageDto<List<BrandDto>> findByPage(Integer page, Integer size);

    BrandDto findById(String id);

    BrandDto save(BrandDto dto);

    BrandDto update(BrandDto dto);

}
