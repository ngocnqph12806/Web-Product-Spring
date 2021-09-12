package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.staff.IntroStaffAdminDto;
import com.example.webproductspringboot.dto.staff.IntroVisitAdminDto;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.dto.staff.FormUserAdminDto;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IUserReponsitory;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserReponsitory _iUserReponsitory;

    @Override
    public List<IntroStaffAdminDto> findAllIntroStaff() {
        List<UserEntity> lst = _iUserReponsitory.findAllStaff();
        Collections.reverse(lst);
        return lst.stream().map(IntroStaffAdminDto::toDto).collect(Collectors.toList());
    }

    @Override
    public Boolean changeStatusStaff(String userNameStaff) {
        Optional<UserEntity> staffEntity = _iUserReponsitory.findByUserName(userNameStaff);
        if (staffEntity.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        UserEntity entity = staffEntity.get();
        entity.setStatus(true);
        if (entity.getStatus()) {
            if (_iUserReponsitory.save(entity) == null) {
                throw new NotFoundException("Kích hoạt tài khoản " + userNameStaff + " thất bại");
            }
        }
        return entity.getStatus();
    }

    @Override
    public Boolean changeBlockStaff(String userNameStaff) {
        Optional<UserEntity> staffEntity = _iUserReponsitory.findByUserName(userNameStaff);
        if (staffEntity.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        UserEntity entity = staffEntity.get();
        entity.setBlock(!entity.getBlock());
        if (!entity.getBlock()) {
            if (_iUserReponsitory.save(entity) == null) {
                throw new NotFoundException("Khoá tài khoản " + userNameStaff + " thất bại");
            }
        } else {
            if (_iUserReponsitory.save(entity) == null) {
                throw new NotFoundException("Mở khoá tài khoản " + userNameStaff + " thất bại");
            }
        }
        return entity.getBlock();
    }

    @Override
    public FormUserAdminDto findByUserName(String userNameStaff) {
        Optional<UserEntity> staffEntity = _iUserReponsitory.findByUserName(userNameStaff);
        if (staffEntity.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        return FormUserAdminDto.toDto(staffEntity.get());
    }

    @Override
    public FormUserAdminDto saveInfoStaff(FormUserAdminDto formUserAdminDto) {
        UserEntity userEntity = formUserAdminDto.toEntity();
        if (userEntity.getId() == null || userEntity.getId().isBlank() || userEntity.getId().isEmpty()) {
            userEntity = userEntity.toBuilder()
                    .id(UUID.randomUUID().toString())
                    .password("12345678")
                    .status(false)
                    .block(false)
                    .created(new Date())
                    .build();
        } else {
            Optional<UserEntity> entity = _iUserReponsitory.findById(formUserAdminDto.getIdUser());
            if (entity.isEmpty()) throw new NotFoundException("Tài khoản không tồn tại");
            else {
                UserEntity fake = entity.get();
                userEntity = userEntity.toBuilder()
                        .password(fake.getPassword())
                        .status(fake.getStatus())
                        .block(fake.getBlock())
                        .created(fake.getCreated())
                        .build();
            }
        }
        userEntity = _iUserReponsitory.save(userEntity);
        if (userEntity == null) {
            throw new InternalServerException("Lưu không thành công");
        }
        return FormUserAdminDto.toDto(userEntity);
    }

    @Override
    public List<IntroVisitAdminDto> findAllIntroVisit() {
        List<UserEntity> lst = _iUserReponsitory.findAllVisit();
        Collections.reverse(lst);
        return lst.stream().map(IntroVisitAdminDto::toDto).collect(Collectors.toList());
    }
}
