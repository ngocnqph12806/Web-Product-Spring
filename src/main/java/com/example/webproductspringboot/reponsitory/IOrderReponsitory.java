package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderReponsitory extends JpaRepository<OrderEntity, String> {
}
