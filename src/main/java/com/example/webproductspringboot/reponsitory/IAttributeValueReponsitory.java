package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.AttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttributeValueReponsitory extends JpaRepository<AttributeValueEntity, String> {
}
