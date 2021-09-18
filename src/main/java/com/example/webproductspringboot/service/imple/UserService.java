package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService  implements IUserService {

    @Autowired
    private IUserReponsitory _iUserReponsitory;

    @Override
    public List<UserDto> findAll() {
        return _iUserReponsitory.findAll().stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
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
        if (optional.isEmpty()) {
            throw new InternalServerException("Người dùng không tồn tại");
        }
        return (UserDto) map(optional.get());
    }

    @Override
    public UserDto save(UserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setBlock(false);
        entity.setCreated(new Date());
        if (_iUserReponsitory.save(entity) == null) {
            throw new InternalServerException("Lưu thất bại");
        }
        return (UserDto) map(entity);
    }

    @Override
    public UserDto update(UserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity.getId() == null || entity.getId().isEmpty() || entity.getId().isBlank()) {
            throw new BadRequestException("Người dùng không đúng");
        }
        if (findById(entity.getId()) == null) {
            throw new InternalServerException("Người dùng không tồn tại");
        }
        if (_iUserReponsitory.save(entity) == null) {
            throw new InternalServerException("Lưu thất bại");
        }
        return (UserDto) map(entity);
    }

//    private Object toObj(Object data) {
//        if (data == null) return null;
//        if (data instanceof UserDto) {
//            UserDto dto = (UserDto) data;
//            return UserEntity.builder()
//                    .id(dto.getId())
//                    .fullName(dto.getFullName())
//                    .dateOfBirth(dto.getDateOfBirth())
//                    .phoneNumber(dto.getPhoneNumber())
//                    .email(dto.getEmail())
//                    .username(dto.getUsername())
//                    .address(dto.getAddress())
//                    .avatar(dto.getAvatar())
//                    .role(dto.getRole())
//                    .status(dto.getStatus())
//                    .block(dto.getBlock())
//                    .build();
//        } else if (data instanceof UserEntity) {
//            UserEntity entity = (UserEntity) data;
//            return UserDto.builder()
//                    .id(entity.getId())
//                    .fullName(entity.getFullName())
//                    .dateOfBirth(entity.getDateOfBirth())
//                    .phoneNumber(entity.getPhoneNumber())
//                    .email(entity.getEmail())
//                    .username(entity.getUsername())
//                    .address(entity.getAddress())
//                    .avatar(entity.getAvatar())
//                    .role(entity.getRole())
//                    .status(entity.getStatus())
//                    .block(entity.getBlock())
//                    .dateCreated(entity.getCreated())
//                    .build();
//        }
//        return null;
//    }

}
