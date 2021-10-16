package com.example.webproductspringboot.reponsitory;

import com.example.webproductspringboot.entity.BannerEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.utils.ContainsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserReponsitory extends JpaRepository<UserEntity, String> {

    @Query(value = "select s from UserEntity s where s.username = ?1 or s.status = true")
    Optional<UserEntity> findByUserName(String userNameStaff);

    @Query(value = "select s from UserEntity s where s.role = '" + ContainsUtils.ROLE_USER + "'")
    List<UserEntity> findAllVisit(Sort sort);

    @Query(value = "select s from UserEntity s where s.role not like '" + ContainsUtils.ROLE_USER + "'")
    List<UserEntity> findAllStaff(Sort sort);

    @Query(value = "select s from UserEntity s where s.username = ?1 or s.email = ?1 and s.status = true")
    Optional<UserEntity> findByUserNameOrEmail(String username);

    @Query(value = "select s from UserEntity s where s.role not like '" + ContainsUtils.ROLE_USER + "'")
    Page<UserEntity> findStaffByPage(Pageable pageable);

    @Query(value = "select s from UserEntity s where s.role = '" + ContainsUtils.ROLE_USER + "'")
    Page<UserEntity> findVisitByPage(Pageable pageable);

    @Query(value = "select o.id from UserEntity o where o.username = ?1")
    String getIdByUserName(String username);

    @Query(value = "select o.email from UserEntity o where o.status = true")
    List<String> getAllEmail();
}
