package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select o from ProductEntity o " +
            "where o.name like %:name% " +
            "or o.idBrand.id like %:idBrand% " +
            "or o.idBrand.name like %:nameBrand% " +
            "or o.idCategory.id like %:idCategory% " +
            "or o.idCategory.name like %:nameCategory% " +
            "or o.price = :price " +
            "or o.priceSale = :priceSale " +
            "or o.quantity = :quantity " +
            "or o.color like %:color% " +
            "or o.categoryDetails like %:categoryDetails% " +
            "or o.location like %:location% " +
            "or o.pathUrl like %:path% " +
            "or o.idUrl = :idPath " +
            "or o.pathUserManual like %:pathUserManual% " +
            "or o.description like %:description% " +
            "or o.status = :status " +
            "or o.created = :dateCreated "
    )
    Page<ProductEntity> findByPageAndSortWithFilter(
            @Param("name") String name,
            @Param("idBrand") String idBrand,
            @Param("nameBrand") String nameBrand,
            @Param("idCategory") String idCategory,
            @Param("nameCategory") String nameCategory,
            @Param("price") Long price,
            @Param("priceSale") Long priceSale,
            @Param("quantity") Integer quantity,
            @Param("color") String color,
            @Param("categoryDetails") String categoryDetails,
            @Param("location") String location,
            @Param("path") String path,
            @Param("idPath") Long idPath,
            @Param("pathUserManual") String pathUserManual,
            @Param("description") String description,
            @Param("status") String status,
            @Param("dateCreated") String dateCreated,
            Pageable pageable);
}
