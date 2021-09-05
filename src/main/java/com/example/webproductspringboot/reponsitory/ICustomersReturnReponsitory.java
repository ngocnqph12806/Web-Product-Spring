package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CustomersReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomersReturnReponsitory extends JpaRepository<CustomersReturnEntity, String> {
}
