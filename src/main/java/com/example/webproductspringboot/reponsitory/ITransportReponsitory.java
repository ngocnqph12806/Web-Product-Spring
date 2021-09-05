package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransportReponsitory extends JpaRepository<TransportEntity, String> {
}
