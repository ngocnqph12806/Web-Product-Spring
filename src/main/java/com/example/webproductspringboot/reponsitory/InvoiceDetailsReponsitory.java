package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.InvoiceDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailsReponsitory extends JpaRepository<InvoiceDetailsEntity, String> {
}
