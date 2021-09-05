package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.ProductDto;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Override
    public ProductDto saveDto(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto newDto(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto findById(String s) {
        return null;
    }

    @Override
    public List<ProductDto> findAll() {
        return null;
    }
}
