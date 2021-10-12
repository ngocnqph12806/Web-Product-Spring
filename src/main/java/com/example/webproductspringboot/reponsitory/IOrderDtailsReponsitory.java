package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IOrderDtailsReponsitory extends JpaRepository<OrderDetailsEntity, String> {

    @Transactional
    @Modifying
    @Query("delete from OrderDetailsEntity l where l.idOrder =:id")
    void deleteAllByIdOrder(@Param("id") String id);
}
