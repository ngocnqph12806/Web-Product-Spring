package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitReponsitory extends JpaRepository<VisitEntity, String> {
}
