package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.category.SearchCategoryDto;

public interface ICategoryService  {
    SearchCategoryDto findById(String category);

    SearchCategoryDto findByIdAndPath(String idCategory, String pathUrl);
}
