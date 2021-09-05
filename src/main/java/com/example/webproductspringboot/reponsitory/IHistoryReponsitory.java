package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoryReponsitory extends JpaRepository<HistoryEntity, String> {
}
