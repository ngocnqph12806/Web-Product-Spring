package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageReponsitory extends JpaRepository<ProductImageEntity, String> {
}
