package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.CategoryDto;
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
    public CategoryDto findById(String category) {
//        return CategoryDto.toDto(_iCategoryReponsitory.findById(category).orElse(null));
        return null;
    }

    @Override
    public CategoryDto findByIdAndPath(String idCategory, String pathUrl) {
//        return CategoryDto.toDto(_iCategoryReponsitory.findByIdAndPath(idCategory, pathUrl).orElse(null));
        return null;
    }

    @Override
    public List<CategoryDto> findAllIntroCategoryAdmin() {
//        return _iCategoryReponsitory.findAll().stream().map(CategoryDto::toDto).collect(Collectors.toList());
        return null;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
//        CategoryEntity categoryEntity = dto.toEntity();
//        if (categoryEntity.getId() == null || categoryEntity.getId().isBlank() || categoryEntity.getId().isEmpty()) {
//            categoryEntity = categoryEntity.toBuilder()
//                    .id(UUID.randomUUID().toString())
//                    .idUrl(new Random().nextLong())
//                    .status(true)
//                    .created(new Date(System.currentTimeMillis()))
//                    .build();
//        } else {
//            Optional<CategoryEntity> entity = _iCategoryReponsitory.findById(categoryEntity.getId());
//            if (entity.isEmpty()) {
//                throw new NotFoundException("Thương hiệu không tồn tại");
//            }
//            CategoryEntity fake = entity.get();
//            categoryEntity = categoryEntity.toBuilder()
//                    .idUrl(fake.getIdUrl())
//                    .status(fake.getStatus())
//                    .created(fake.getCreated())
//                    .build();
//        }
//        categoryEntity = _iCategoryReponsitory.save(categoryEntity);
//        if (categoryEntity == null) {
//            throw new InternalServerException("Lưu thất bại");
//        }
//        return CategoryDto.toDto(categoryEntity);
        return null;
    }

    @Override
    public CategoryDto findIntroById(String id) {
        return null;
    }

    @Override
    public CategoryDto findFormById(String id) {
//        Optional<CategoryEntity> optional = _iCategoryReponsitory.findById(id);
//        if (optional.isEmpty()) {
//            throw new NotFoundException("Loại sản phẩm không tồn tại");
//        }
//        return CategoryDto.toDto(optional.get());
        return null;
    }

    private Object toObj(Object data) {
        if (data == null) return null;
        if (data instanceof CategoryDto) {
            CategoryDto dto = (CategoryDto) data;
            return CategoryEntity.builder()
                    .build();
        } else if (data instanceof CategoryEntity) {
            CategoryEntity entity = (CategoryEntity) data;
            return CategoryDto.builder()
                    .build();
        }
        return null;
    }

}
