package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceReponsitory extends JpaRepository<InvoiceEntity, String> {
}
