package com.example.webproductspringboot.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.vo.SearchUserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserApi {

    @Autowired
    private IUserService _iUserService;

    @GetMapping(path = "")
    public ResponseEntity<?> getAll(SearchUserVo searchUserVo) {
        List<UserDto> lst = _iUserService.findAll();
        lst = searchByFullName(searchUserVo.getFullName(), lst);
        lst = searchByDateOfBirth(searchUserVo.getDateOfBirth(), lst);
        lst = searchByEmail(searchUserVo.getEmail(), lst);
        lst = searchByPhoneNumber(searchUserVo.getPhoneNumber(), lst);
        lst = searchByUsername(searchUserVo.getUsername(), lst);
        lst = searchByAddress(searchUserVo.getAddress(), lst);
        lst = searchByAvatar(searchUserVo.getAvatar(), lst);
        lst = searchByRole(searchUserVo.getRole(), lst);
        lst = searchByStatus(searchUserVo.getStatus(), lst);
        lst = searchByBlock(searchUserVo.getBlock(), lst);
        lst = searchByDaetCreate(searchUserVo.getDateCreated(), lst);
        ResultDto<List<UserDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        ResultDto<UserDto> result = new ResultDto<>(true, "", _iUserService.findById(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<UserDto> result = new ResultDto<>(true, "", null);
        try {
            result.setData(_iUserService.findById(id));
        } catch (Exception e) {
            result.setData(new UserDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Validated @RequestBody ChangeUserDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<UserDto> result = new ResultDto<>(true, "Đã thêm mới người dùng", _iUserService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid ChangeUserDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<UserDto> result = new ResultDto<UserDto>(true, "Đã chỉnh sửa thông tin người dùng", _iUserService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<UserDto> searchByFullName(String[] fullName, List<UserDto> lst) {
        if (fullName == null) return lst;
        for (String x : fullName) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getFullName().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByDateOfBirth(String[] dateOfBirth, List<UserDto> lst) {
        if (dateOfBirth == null) return lst;
        for (String x : dateOfBirth) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateOfBirth()).contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByPhoneNumber(String[] phoneNumber, List<UserDto> lst) {
        if (phoneNumber == null) return lst;
        for (String x : phoneNumber) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByEmail(String[] email, List<UserDto> lst) {
        if (email == null) return lst;
        for (String x : email) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getEmail().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByUsername(String[] username, List<UserDto> lst) {
        if (username == null) return lst;
        for (String x : username) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getUsername().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByAddress(String[] address, List<UserDto> lst) {
        if (address == null) return lst;
        for (String x : address) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getAddress().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByAvatar(String[] avatar, List<UserDto> lst) {
        if (avatar == null) return lst;
        for (String x : avatar) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getAvatar().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByRole(String[] role, List<UserDto> lst) {
        if (role == null) return lst;
        for (String x : role) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> e.getRole().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByStatus(String[] status, List<UserDto> lst) {
        if (status == null) return lst;
        for (String x : status) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> Boolean.parseBoolean(x) == e.getStatus())
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByBlock(String[] block, List<UserDto> lst) {
        if (block == null) return lst;
        for (String x : block) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> Boolean.parseBoolean(x) == e.getBlock())
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByDaetCreate(String[] dateCreated, List<UserDto> lst) {
        if (dateCreated == null) return lst;
        for (String x : dateCreated) {
            if (lst.isEmpty()) return lst;
            lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }
}
