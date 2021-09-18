package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.ProductDto;

import java.util.List;
import java.util.Set;

public interface IProductService {

    List<ProductDto> findAll();

    ProductDto save(ProductDto dto);

    Set<String> getSetCategoryDetailsByIdCategory(String idCategory);

    Set<String> getSetColorByIdCategory(String idCategory);

    ProductDto getMinMaxPrice();
}
