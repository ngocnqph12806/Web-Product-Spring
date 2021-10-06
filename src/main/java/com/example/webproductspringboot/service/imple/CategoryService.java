package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService extends AbstractService implements ICategoryService {

    private final ICategoryReponsitory _iCategoryReponsitory;

    protected CategoryService(HttpServletRequest request, ICategoryReponsitory iCategoryReponsitory) {
        super(request);
        _iCategoryReponsitory = iCategoryReponsitory;
    }


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
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "category", "category.not.found"));
        }
        return (CategoryDto) map(optional.get());
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        CategoryEntity entity = (CategoryEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        String idUrl = String.valueOf(new Random().nextInt(20) * 1000000000);
        entity.setIdUrl(Long.parseLong(idUrl.replaceAll("-", "")));
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iCategoryReponsitory.save(entity);
        saveHistory(userEntity, "Thêm loại sản phẩm", entity.toString());
        return (CategoryDto) map(entity);
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        CategoryEntity entity = (CategoryEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<CategoryEntity> optional = _iCategoryReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "category", "category.not.found"));
        CategoryEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setIdUrl(fake.getIdUrl());
        entity.setCreated(fake.getCreated());
        _iCategoryReponsitory.save(entity);
        saveHistory(userEntity, "Sửa loại sản phẩm", fake + "\n" + entity);
        return (CategoryDto) map(entity);
    }

}
