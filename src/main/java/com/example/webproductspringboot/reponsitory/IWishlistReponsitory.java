package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.WishlistEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IWishlistReponsitory extends JpaRepository<WishlistEntity, String> {

    @Query(value = "select o from WishlistEntity o where o.idVisit.id = ?1")
    List<WishlistEntity> findAllByUserLogin(String idVisit);

    @Query(value = "select o from WishlistEntity o where o.idProduct.id = ?1 and o.idVisit.id = ?2")
    Optional<WishlistEntity> existWishlist(String idProduct, String id);

}
