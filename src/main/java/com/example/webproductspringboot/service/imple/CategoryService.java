package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.category.SearchCategoryDto;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryReponsitory _iCategoryReponsitory;

    @Override
    public SearchCategoryDto findById(String category) {
        return SearchCategoryDto.toDto(_iCategoryReponsitory.findById(category).orElse(null));
    }

    @Override
    public SearchCategoryDto findByIdAndPath(String idCategory, String pathUrl) {
        return SearchCategoryDto.toDto(_iCategoryReponsitory.findByIdAndPath(idCategory, pathUrl).orElse(null));
    }
}
