package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.product.FormCategoryAdminDto;
import com.example.webproductspringboot.dto.product.IntroCategoryAdminDto;
import com.example.webproductspringboot.dto.product.SearchCategoryDto;

import java.util.List;

public interface ICategoryService  {
    SearchCategoryDto findById(String category);

    SearchCategoryDto findByIdAndPath(String idCategory, String pathUrl);

    List<IntroCategoryAdminDto> findAllIntroCategoryAdmin();

    IntroCategoryAdminDto save(FormCategoryAdminDto dto);

    IntroCategoryAdminDto findIntroById(String id);
}
