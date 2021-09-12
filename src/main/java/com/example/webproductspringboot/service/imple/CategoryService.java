package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.FormCategoryAdminDto;
import com.example.webproductspringboot.dto.product.IntroCategoryAdminDto;
import com.example.webproductspringboot.dto.product.SearchCategoryDto;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<IntroCategoryAdminDto> findAllIntroCategoryAdmin() {
        return _iCategoryReponsitory.findAll().stream().map(IntroCategoryAdminDto::toDto).collect(Collectors.toList());
    }

    @Override
    public IntroCategoryAdminDto save(FormCategoryAdminDto dto) {
        return null;
    }

    @Override
    public IntroCategoryAdminDto findIntroById(String id) {
        return null;
    }
}
