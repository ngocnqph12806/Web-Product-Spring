package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ReviewProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewProductReponsitory extends JpaRepository<ReviewProductEntity, String> {
}
