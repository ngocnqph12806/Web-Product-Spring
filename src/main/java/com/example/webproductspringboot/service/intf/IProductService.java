package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.product.DetailsProductDto;
import com.example.webproductspringboot.dto.product.FilterPriceProductDto;
import com.example.webproductspringboot.dto.product.FormProductDto;
import com.example.webproductspringboot.dto.product.IntroProductDto;

import java.util.List;
import java.util.Set;

public interface IProductService  {

    List<IntroProductDto> findBestSallers();

    List<IntroProductDto> findAll();

    List<IntroProductDto> findByIdCategory(String idCategory);

    DetailsProductDto findByPath(Long idUrl,String pathUrl);

    Set<String> getSetCategoryDetailsByIdCategory(String idCategory);

    Set<String> getSetColorByIdCategory(String idCategory);

    FilterPriceProductDto getMinMaxPrice();

    IntroProductDto findIntroById(String id);

    IntroProductDto save(FormProductDto dto);
}
