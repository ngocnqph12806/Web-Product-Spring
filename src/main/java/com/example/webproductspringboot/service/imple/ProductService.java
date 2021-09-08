package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.IntroProductDto;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductReponsitory _iProductReponsitory;

    @Override
    public List<IntroProductDto> findBestSallers() {
        return _iProductReponsitory.findBestSallers().stream().map(IntroProductDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductDto> findAll() {
        return _iProductReponsitory.findAll().stream().map(IntroProductDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductDto> findByIdCategory(String idCategory) {
        return _iProductReponsitory.findByIdCategory(idCategory).stream().map(IntroProductDto::toDto).collect(Collectors.toList());
    }
}
