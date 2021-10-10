package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.InvoiceDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface InvoiceDetailsReponsitory extends JpaRepository<InvoiceDetailsEntity, String> {

    @Transactional
    @Modifying
    @Query("delete from InvoiceDetailsEntity l where l.idInvoice =:id")
    void deleteAllByIdInvoice(@Param("id") String id);

}
