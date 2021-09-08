package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.product.IntroProductDto;

import java.util.List;

public interface IProductService  {

    List<IntroProductDto> findBestSallers();

    List<IntroProductDto> findAll();

    List<IntroProductDto> findByIdCategory(String idCategory);
}
