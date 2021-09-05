package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.ProductDetailsDto;
import com.example.webproductspringboot.service.intf.IProductDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsService implements IProductDetailsService {
    @Override
    public ProductDetailsDto saveDto(ProductDetailsDto productDetailsDto) {
        return null;
    }

    @Override
    public ProductDetailsDto newDto(ProductDetailsDto productDetailsDto) {
        return null;
    }

    @Override
    public ProductDetailsDto findById(String s) {
        return null;
    }

    @Override
    public List<ProductDetailsDto> findAll() {
        return null;
    }
}
