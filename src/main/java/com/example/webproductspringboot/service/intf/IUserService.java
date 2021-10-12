package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.vo.InfoCheckoutVo;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();

    List<UserDto> findAllStaff();

    List<UserDto> findAllVisit();

    PageDto<List<UserDto>> findAll(Integer page, Integer size);

    PageDto<List<UserDto>> findStaffByPage(Integer page, Integer size);

    PageDto<List<UserDto>> findVisitByPage(Integer page, Integer size);

    UserDto findById(String id);

    UserDto findByUserName(String username);

    UserDto save(UserDto dto);

    UserDto update(UserDto dto);

    InfoCheckoutVo getInfoCheckoutByUserLogin(String id);

    String getIdByUserName(String username);
}
