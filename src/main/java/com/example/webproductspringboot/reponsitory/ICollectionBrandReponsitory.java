package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CollectionBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICollectionBrandReponsitory extends JpaRepository<CollectionBrandEntity, String> {
}
