package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductReponsitory extends JpaRepository<ProductEntity, String> {
}
