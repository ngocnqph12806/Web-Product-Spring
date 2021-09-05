package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.ProductImageDto;
import com.example.webproductspringboot.service.intf.IProductImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService implements IProductImageService {
    @Override
    public ProductImageDto saveDto(ProductImageDto productImageDto) {
        return null;
    }

    @Override
    public ProductImageDto newDto(ProductImageDto productImageDto) {
        return null;
    }

    @Override
    public ProductImageDto findById(String s) {
        return null;
    }

    @Override
    public List<ProductImageDto> findAll() {
        return null;
    }
}
