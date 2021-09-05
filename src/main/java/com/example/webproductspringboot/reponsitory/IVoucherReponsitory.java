package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoucherReponsitory extends JpaRepository<VoucherEntity, String> {
}
