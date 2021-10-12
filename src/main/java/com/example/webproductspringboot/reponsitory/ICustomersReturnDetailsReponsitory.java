package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.CustomersReturnDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ICustomersReturnDetailsReponsitory extends JpaRepository<CustomersReturnDetailsEntity, String> {
    @Transactional
    @Modifying
    @Query("delete from CustomersReturnDetailsEntity l where l.idCustomersReturn =:id")
    void deleteByIdOrder(@Param("id") String idReturn);
}
