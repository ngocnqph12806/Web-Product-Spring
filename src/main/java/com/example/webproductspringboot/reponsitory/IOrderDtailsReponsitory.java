package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDtailsReponsitory extends JpaRepository<OrderDetailsEntity, String> {
}
