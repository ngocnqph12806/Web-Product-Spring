package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.BannerEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.utils.ContainsUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserReponsitory extends JpaRepository<UserEntity, String> {

    @Query(value = "select s from UserEntity s where s.username = ?1")
    Optional<UserEntity> findByUserName(String userNameStaff);

}
