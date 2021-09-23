package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IProductImageReponsitory extends JpaRepository<ProductImageEntity, String> {

    @Transactional
    @Modifying
    @Query("delete from ProductImageEntity l where l.idProduct.id =:id")
    void deleteAllImagesByProductId(@Param("id") String id);
}
