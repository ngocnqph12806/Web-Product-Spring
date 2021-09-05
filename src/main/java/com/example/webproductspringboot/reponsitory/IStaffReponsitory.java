package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffReponsitory extends JpaRepository<StaffEntity, String> {
}
