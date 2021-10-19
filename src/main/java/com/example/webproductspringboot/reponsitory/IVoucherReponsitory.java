package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.VoucherEntity;
import com.example.webproductspringboot.vo.VoucherApplyVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IVoucherReponsitory extends JpaRepository<VoucherEntity, String> {

    @Query(value = "select o from VoucherEntity o where o.code = ?1 and o.quantity > 0 and o.status = true")
    Optional<VoucherEntity> findByCode(String code);
}
