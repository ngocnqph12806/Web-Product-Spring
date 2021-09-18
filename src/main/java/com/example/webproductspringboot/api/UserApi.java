package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.vo.SearchUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private IUserService _iUserService;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") String id) {
        return _iUserService.findById(id);
    }

    @GetMapping(path = "")
    public List<UserDto> getAll(SearchUserVo searchUserVo) {
        System.out.println(searchUserVo);
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
        return lst;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Validated @RequestBody Object dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<Object> result = new ResultDto<>(true, "Lưu thành công", null);
        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid UserDto formUserAdminDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<UserDto> result = new ResultDto<UserDto>(true, "Lưu thành công", _iUserService.save(formUserAdminDto));
        return ResponseEntity.ok(result);
    }


    private List<UserDto> searchByFullName(String[] fullName, List<UserDto> lst) {
        if(fullName == null) return lst;
        for (String x : fullName) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getFullName().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByDateOfBirth(String[] dateOfBirth, List<UserDto> lst) {
        if(dateOfBirth == null) return lst;
        for (String x : dateOfBirth) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateOfBirth()).contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByPhoneNumber(String[] phoneNumber, List<UserDto> lst) {
        if(phoneNumber == null) return lst;
        for (String x : phoneNumber) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByEmail(String[] email, List<UserDto> lst) {
        if(email == null) return lst;
        for (String x : email) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getEmail().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByUsername(String[] username, List<UserDto> lst) {
        if(username == null) return lst;
        for (String x : username) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getUsername().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByAddress(String[] address, List<UserDto> lst) {
        if(address == null) return lst;
        for (String x : address) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getAddress().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByAvatar(String[] avatar, List<UserDto> lst) {
        if(avatar == null) return lst;
        for (String x : avatar) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getAvatar().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByRole(String[] role, List<UserDto> lst) {
        if(role == null) return lst;
        for (String x : role) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getRole().contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByStatus(String[] status, List<UserDto> lst) {
        if(status == null) return lst;
        for (String x : status) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getStatus().equals(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByBlock(String[] block, List<UserDto> lst) {
        if(block == null) return lst;
        for (String x : block) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> e.getBlock().equals(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }

    private List<UserDto> searchByDaetCreate(String[] dateCreated, List<UserDto> lst) {
        if(dateCreated == null) return lst;
        for (String x : dateCreated) {
            if (lst.isEmpty()) {
                return lst;
            }
            lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x))
                    .collect(Collectors.toList());
        }
        return lst;
    }
}
