package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.CategoryDto;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Override
    public CategoryDto saveDto(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto newDto(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto findById(String s) {
        return null;
    }

    @Override
    public List<CategoryDto> findAll() {
        return null;
    }
}
