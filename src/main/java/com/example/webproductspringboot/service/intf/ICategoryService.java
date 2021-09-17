package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    CategoryDto findById(String category);

    CategoryDto save(CategoryDto dto);

    CategoryDto findByIdAndPath(String idCategory, String pathUrl);

    List<CategoryDto> findAllIntroCategoryAdmin();

    CategoryDto findIntroById(String id);

    CategoryDto findFormById(String id);
}
