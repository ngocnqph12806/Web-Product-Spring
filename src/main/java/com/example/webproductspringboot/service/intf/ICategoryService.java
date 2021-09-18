package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> findAll();

    CategoryDto findById(String id);

    CategoryDto save(CategoryDto dto);
}
