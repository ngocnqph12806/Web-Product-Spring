package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.OrderDtailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDtailsReponsitory extends JpaRepository<OrderDtailsEntity, String> {
}
