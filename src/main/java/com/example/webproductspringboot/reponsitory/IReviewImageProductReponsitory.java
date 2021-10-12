package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ReviewImageEntity;
import com.example.webproductspringboot.entity.ReviewProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewImageProductReponsitory extends JpaRepository<ReviewImageEntity, String> {
}
