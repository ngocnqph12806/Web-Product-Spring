package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ValueDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IValueDetailsReponsitory extends JpaRepository<ValueDetailsEntity, String> {
}
