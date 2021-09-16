package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICategoryReponsitory extends JpaRepository<CategoryEntity, String> {

    @Query(value = "select o from CategoryEntity o where o.id = ?1 and o.pathUrl = ?2 and o.status = true")
    Optional<CategoryEntity> findByIdAndPath(String idCategory, String pathUrl);

    @Override
    @Query(value = "select o from CategoryEntity o where o.status = true")
    List<CategoryEntity> findAll();
}
