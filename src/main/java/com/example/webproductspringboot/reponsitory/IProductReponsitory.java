package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IProductReponsitory extends JpaRepository<ProductEntity, String> {

    @Override
    @Query(value = "select p from ProductEntity p where p.status = true")
    List<ProductEntity> findAll();

    @Query(value = "select p from ProductEntity p, " +
            "OrderDetailsEntity od " +
            "where p.id = od.idProduct and p.status = true " +
            "group by od.id " +
            "order by count(od.id) desc")
    List<ProductEntity> findBestSallers();

    @Query(value = "select p from ProductEntity p where p.idCategory.id = ?1 and p.status = true")
    List<ProductEntity> findByIdCategory(String idCategory);

    @Query(value = "select p from ProductEntity p where p.idUrl = ?1 and  p.pathUrl = ?2 and p.status = true")
    Optional<ProductEntity> findByPath(Long idUrl, String pathUrl);

    @Query(value = "select distinct p.categoryDetails from ProductEntity p where p.idCategory.id = ?1 and p.status = true")
    Set<String> getSetCategoryDetailsByIdCategory(String idCategory);

    @Query(value = "select distinct p.color from ProductEntity p where p.idCategory.id = ?1 and p.status = true")
    Set<String> getSetColorByIdCategory(String idCategory);

    @Query(value = "select distinct p.idCategory.name from ProductEntity p where p.status = true")
    Set<String> getAllNameCategory();

    @Query(value = "select distinct p.color from ProductEntity p where p.status = true")
    Set<String> getAllColor();

    @Query(value = "select min(p.price) as priceMin from ProductEntity p where p.idCategory.id = ?1 and p.status = true")
    Long findMinPriceByIdCategory(String idCategory);

    @Query(value = "select max(p.price) as priceMax from ProductEntity p where p.idCategory.id = ?1 and p.status = true")
    Long findMaxPriceByIdCategory(String idCategory);

    @Query(value = "select min(p.price) as priceMin from ProductEntity p where p.status = true")
    Long findMinPrice();

    @Query(value = "select max(p.price) as priceMax from ProductEntity p where p.status = true")
    Long findMaxPrice();

    @Query(value = "select p from ProductEntity p, OrderDetailsEntity ord" +
            " where p.id = ord.idProduct.id" +
            " group by p" +
            " order by count (ord.id) desc")
    List<ProductEntity> getByMostOrder();
}
