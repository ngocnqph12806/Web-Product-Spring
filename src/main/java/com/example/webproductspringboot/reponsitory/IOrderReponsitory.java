package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderReponsitory extends JpaRepository<OrderEntity, String> {


    @Query(value = "select o from OrderEntity o where o.idVisit.id = ?1")
    List<OrderEntity> getAllOrderByUserLogin(String id);
}
