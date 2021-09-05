package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CustomersReturnDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomersReturnDetailsReponsitory extends JpaRepository<CustomersReturnDetailsEntity, String> {
}
