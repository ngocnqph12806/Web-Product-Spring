package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandReponsitory extends JpaRepository<BrandEntity, String> {
}
