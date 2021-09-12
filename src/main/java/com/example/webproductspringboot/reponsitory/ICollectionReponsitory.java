package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICollectionReponsitory extends JpaRepository<CollectionEntity, String> {

    @Override
    @Query(value = "select o from CollectionEntity o where o.status = true")
    List<CollectionEntity> findAll();
}
