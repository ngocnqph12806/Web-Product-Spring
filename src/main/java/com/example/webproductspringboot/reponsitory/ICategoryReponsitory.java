package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryReponsitory extends JpaRepository<CategoryEntity, String> {
}
