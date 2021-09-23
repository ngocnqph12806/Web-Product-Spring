package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService extends AbstractService implements ICategoryService {

    @Autowired
    private ICategoryReponsitory _iCategoryReponsitory;

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryEntity> lst = _iCategoryReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (CategoryDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(String id) {
        Optional<CategoryEntity> optional = _iCategoryReponsitory.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Loại sản phẩm không tồn tại");
        }
        return (CategoryDto) map(optional.get());
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        CategoryEntity entity = (CategoryEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu lỗi");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iCategoryReponsitory.save(entity);
        saveHistory(userEntity, "Thêm loại sản phẩm: \n" + entity);
        return (CategoryDto) map(entity);
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        CategoryEntity entity = (CategoryEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu lỗi");
        UserEntity userEntity = getUserLogin();
        Optional<CategoryEntity> optional = _iCategoryReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Loại sản phẩm không tồn tại");
        CategoryEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setIdUrl(fake.getIdUrl());
        entity.setCreated(fake.getCreated());
        _iCategoryReponsitory.save(entity);
        saveHistory(userEntity, "Sửa loại sản phẩm: \n" + fake + "\n" + entity);
        return (CategoryDto) map(entity);
    }

}
