package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();

    List<UserDto> findAllStaff();

    List<UserDto> findAllVisit();

    PageDto<List<UserDto>> findAll(Integer page, Integer size);

    UserDto findById(String id);

    UserDto save(ChangeUserDto dto);

    UserDto update(ChangeUserDto dto);

    UserDto findByUserName(String username);
}
