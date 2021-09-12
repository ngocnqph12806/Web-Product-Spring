package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBrandReponsitory extends JpaRepository<BrandEntity, String> {

    @Override
    @Query(value = "select o from BrandEntity o where o.status = true")
    List<BrandEntity> findAll();
}
