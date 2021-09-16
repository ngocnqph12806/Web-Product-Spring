package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.partner.IntroBrandAdminDto;
import com.example.webproductspringboot.dto.product.FormCategoryAdminDto;
import com.example.webproductspringboot.dto.product.IntroCategoryAdminDto;
import com.example.webproductspringboot.dto.product.SearchCategoryDto;
import com.example.webproductspringboot.entity.BrandEntity;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        CategoryEntity categoryEntity = dto.toEntity();
        if (categoryEntity.getId() == null || categoryEntity.getId().isBlank() || categoryEntity.getId().isEmpty()) {
            categoryEntity = categoryEntity.toBuilder()
                    .id(UUID.randomUUID().toString())
                    .idUrl(new Random().nextLong())
                    .status(true)
                    .created(new Date(System.currentTimeMillis()))
                    .build();
        } else {
            Optional<CategoryEntity> entity = _iCategoryReponsitory.findById(categoryEntity.getId());
            if (entity.isEmpty()) {
                throw new NotFoundException("Thương hiệu không tồn tại");
            }
            CategoryEntity fake = entity.get();
            categoryEntity = categoryEntity.toBuilder()
                    .idUrl(fake.getIdUrl())
                    .status(fake.getStatus())
                    .created(fake.getCreated())
                    .build();
        }
        categoryEntity = _iCategoryReponsitory.save(categoryEntity);
        if (categoryEntity == null) {
            throw new InternalServerException("Lưu thất bại");
        }
        return IntroCategoryAdminDto.toDto(categoryEntity);
    }

    @Override
    public IntroCategoryAdminDto findIntroById(String id) {
        return null;
    }

    @Override
    public FormCategoryAdminDto findFormById(String id) {
        Optional<CategoryEntity> optional = _iCategoryReponsitory.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Loại sản phẩm không tồn tại");
        }
        return FormCategoryAdminDto.toDto(optional.get());
    }
}
