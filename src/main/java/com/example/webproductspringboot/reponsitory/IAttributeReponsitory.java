package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttributeReponsitory extends JpaRepository<AttributeEntity, String> {
}
