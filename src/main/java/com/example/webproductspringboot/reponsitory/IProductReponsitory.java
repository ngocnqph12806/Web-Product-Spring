package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductReponsitory extends JpaRepository<ProductEntity, String> {

    @Query(value = "select p from ProductEntity p, " +
            "ProductDetailsEntity pd, OrderDetailsEntity od " +
            "where pd.id = od.id and p.status = true " +
            "group by od.id " +
            "order by count(od.id) desc")
    List<ProductEntity> findBestSallers();

    @Query(value = "select p from ProductEntity p where p.idCategory.id = :idCategory and p.status = true")
    List<ProductEntity> findByIdCategory(String idCategory);
}
