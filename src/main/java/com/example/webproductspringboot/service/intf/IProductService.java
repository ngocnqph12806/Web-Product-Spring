package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.entity.ProductEntity;
import com.example.webproductspringboot.model.dto.ProductDto;
import com.example.webproductspringboot.service.IGenericService;

public interface IProductService extends IGenericService<ProductDto, String> {
}
