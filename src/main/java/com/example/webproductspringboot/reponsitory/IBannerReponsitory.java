package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBannerReponsitory extends JpaRepository<BannerEntity, String> {
}
