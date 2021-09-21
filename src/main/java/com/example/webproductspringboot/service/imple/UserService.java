package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.reponsitory.IUserReponsitory;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService implements IUserService {

    @Autowired
    private IUserReponsitory _iUserReponsitory;

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> lst = _iUserReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllStaff() {
        List<UserEntity> lst = _iUserReponsitory.findAllStaff();
        Collections.reverse(lst);
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllVisit() {
        List<UserEntity> lst = _iUserReponsitory.findAllVisit();
        Collections.reverse(lst);
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<UserDto>> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page + 1, size);
        Page<UserEntity> entities = _iUserReponsitory.findAll(pageable);
        return new PageDto<List<UserDto>>(entities.getTotalPages(), entities.getTotalPages(),
                entities.getContent().stream().map(e -> (UserDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public UserDto findById(String id) {
        Optional<UserEntity> optional = _iUserReponsitory.findById(id);
        if (optional.isEmpty()) throw new InternalServerException("Người dùng không tồn tại");
        return (UserDto) map(optional.get());
    }

    @Override
    public UserDto save(ChangeUserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setBlock(false);
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        entity.setCreated(new Date());
        if (_iUserReponsitory.save(entity) == null) {
            throw new InternalServerException("Lưu thất bại");
        }
        return (UserDto) map(entity);
    }

    @Override
    public UserDto update(ChangeUserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        if (entity.getId() == null || entity.getId().isEmpty() || entity.getId().isBlank())
            throw new BadRequestException("Người dùng không đúng");
        Optional<UserEntity> fake = _iUserReponsitory.findById(entity.getId());
        if (fake.isEmpty()) throw new InternalServerException("Người dùng không tồn tại");
        UserEntity entityFake = fake.get();
        entity.setCreated(entityFake.getCreated());
        if (entity.getBlock() == null) {
            entity.setBlock(entityFake.getBlock());
        }
        if (entity.getStatus() == null) {
            entity.setStatus(entityFake.getStatus());
        }
        if (entity.getPassword() == null || entity.getPassword().isEmpty() || entity.getPassword().isBlank()) {
            entity.setPassword(entityFake.getPassword());
        } else {
            entity.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        }
        if (_iUserReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (UserDto) map(entity);
    }

}
