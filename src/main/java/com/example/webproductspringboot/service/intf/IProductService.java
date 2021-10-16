package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.vo.ProductImageVo;
import com.example.webproductspringboot.vo.SearchProductVo;

import java.util.List;
import java.util.Set;

public interface IProductService {

    List<ProductDto> findAllProduct();

    PageDto<List<ProductDto>> findByPage(Integer page, Integer size);

    PageDto<List<ProductDto>> findByPageAndSort(Integer page, Integer size,String field, String sort);

    ProductDto findProductById(String id);

    ProductDto saveProduct(ProductDto dto);

    ProductDto updateProduct(ProductDto dto);

    void saveImageProduct(ProductImageVo vo);

    Set<String> getSetCategoryDetailsByIdCategory(String idCategory);

    Set<String> getSetColorByIdCategory(String idCategory);

    ProductDto getMinMaxPrice();

    void deleteAllImagesByProductId(String id);

    List<ProductDto> getByMostOrder();

//    PageDto<List<ProductDto>> findByPageAndSortWithFilter(SearchProductVo searchProductVo);
}
