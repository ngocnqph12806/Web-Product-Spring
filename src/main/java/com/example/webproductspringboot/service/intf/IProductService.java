package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.vo.ProductImageVo;

import java.util.List;
import java.util.Set;

public interface IProductService {

    List<ProductDto> findAllProduct();

    ProductDto findProductById(String id);

    ProductDto saveProduct(ProductDto dto);

    ProductDto updateProduct(ProductDto dto);

    ProductImageVo saveImageProduct(ProductImageVo vo);

    Set<String> getSetCategoryDetailsByIdCategory(String idCategory);

    Set<String> getSetColorByIdCategory(String idCategory);

    ProductDto getMinMaxPrice();

    void deleteAllImagesByProductId(String id);
}
