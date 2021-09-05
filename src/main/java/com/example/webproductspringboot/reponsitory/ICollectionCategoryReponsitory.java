package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CollectionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICollectionCategoryReponsitory extends JpaRepository<CollectionCategoryEntity, String> {
}
