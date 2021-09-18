package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService extends AbstractService  implements ICategoryService {

    @Autowired
    private ICategoryReponsitory _iCategoryReponsitory;

    @Override
    public List<CategoryDto> findAll() {
        return null;
    }

    @Override
    public CategoryDto findById(String id) {
        return null;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return null;
    }

//    private Object toObj(Object data) {
//        if (data == null) return null;
//        if (data instanceof CategoryDto) {
//            CategoryDto dto = (CategoryDto) data;
//            return CategoryEntity.builder()
//                    .id(dto.getId())
//                    .name(dto.getName())
//                    .banner(dto.getBanner())
//                    .pathUrl(dto.getPath())
//                    .description(dto.getDescription())
//                    .status(dto.getStatus())
//                    .build();
//        } else if (data instanceof CategoryEntity) {
//            CategoryEntity entity = (CategoryEntity) data;
//            return CategoryDto.builder()
//                    .id(entity.getId())
//                    .name(entity.getName())
//                    .banner(entity.getBanner())
//                    .path(entity.getPathUrl())
//                    .description(entity.getDescription())
//                    .status(entity.getStatus())
//                    .dateCreated(entity.getCreated())
//                    .countProducts(entity.getLstProductEntities() != null
//                            ? entity.getLstProductEntities().size() : 0)
//                    .build();
//        }
//        return null;
//    }
}
