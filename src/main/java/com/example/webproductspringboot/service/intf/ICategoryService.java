package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.dto.PageDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> findAll();

    PageDto<List<CategoryDto>> findByPage(Integer page, Integer size);

    CategoryDto findById(String id);

    CategoryDto save(CategoryDto dto);

    CategoryDto update(CategoryDto dto);

}
