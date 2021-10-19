package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderReponsitory extends JpaRepository<OrderEntity, String> {


    @Query(value = "select o from OrderEntity o where o.idVisit.id = ?1 ORDER BY o.created desc")
    List<OrderEntity> getAllOrderByUserLogin(String id);

    @Query(value = "CALL tinh_doanh_thu(?1)", nativeQuery = true)
    Long finDoanhSoTrongThang(int i);

    @Query(value = "CALL tinh_doanh_thu_2(?1)", nativeQuery = true)
    Long finDoanhThuTrongThang(int i);
}
