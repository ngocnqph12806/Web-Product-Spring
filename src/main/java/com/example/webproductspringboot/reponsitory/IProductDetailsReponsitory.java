package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ProductDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDetailsReponsitory extends JpaRepository<ProductDetailsEntity, String> {
}
