package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBannerReponsitory extends JpaRepository<BannerEntity, String> {
}
