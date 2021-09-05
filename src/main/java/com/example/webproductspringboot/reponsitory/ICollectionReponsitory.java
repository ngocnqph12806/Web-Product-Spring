package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICollectionReponsitory extends JpaRepository<CollectionEntity, String> {
}
