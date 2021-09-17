package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();

    PageDto<List<UserDto>> findAll(Integer page, Integer size);

    UserDto findById(String id);

    UserDto save(UserDto dto);

    UserDto update(UserDto dto);

}
