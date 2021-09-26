package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBrandVo;
import com.example.webproductspringboot.vo.SearchUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserApi extends AbstractApi {

    private final IUserService _iUserService;

    protected UserApi(HttpServletRequest request, IUserService iUserService) {
        super(request);
        _iUserService = iUserService;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAll(SearchUserVo searchUserVo) {
        List<UserDto> lst = _iUserService.findAll();
        System.out.println(searchUserVo);
        lst = search(lst, searchUserVo, searchUserVo.getFullName(), 0);
//        lst = searchByFullName(searchUserVo.getFullName(), lst);
//        lst = searchByDateOfBirth(searchUserVo.getDateOfBirth(), lst);
//        lst = searchByEmail(searchUserVo.getEmail(), lst);
//        lst = searchByPhoneNumber(searchUserVo.getPhoneNumber(), lst);
//        lst = searchByUsername(searchUserVo.getUsername(), lst);
//        lst = searchByAddress(searchUserVo.getAddress(), lst);
//        lst = searchByAvatar(searchUserVo.getAvatar(), lst);
//        lst = searchByRole(searchUserVo.getRole(), lst);
//        lst = searchByStatus(searchUserVo.getStatus(), lst);
//        lst = searchByBlock(searchUserVo.getBlock(), lst);
//        lst = searchByDaetCreate(searchUserVo.getDateCreated(), lst);
        ResultDto<List<UserDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        ResultDto<UserDto> result = new ResultDto<>(OK, _iUserService.findById(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<UserDto> result = new ResultDto<>(OK, null);
        try {
            result.setData(_iUserService.findById(id));
        } catch (Exception e) {
            result.setData(new UserDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable("id") String id,
                                  @Validated @RequestBody ChangeUserDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<UserDto> result = new ResultDto<>(CREATED, _iUserService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ChangeUserDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors()) {
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", errors.getFieldErrors().get(0).getDefaultMessage()));
        }
        ResultDto<UserDto> result = new ResultDto<UserDto>(UPDATED, _iUserService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<UserDto> search(List<UserDto> lst, SearchUserVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            if (lst.isEmpty()) return lst;
            if (index == 0)
                lst = lst.stream().filter(e -> e.getFullName().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 1)
                lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateOfBirth()).contains(x)).collect(Collectors.toList());
            else if (index == 2)
                lst = lst.stream().filter(e -> e.getEmail().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 3)
                lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x)).collect(Collectors.toList());
            else if (index == 4)
                lst = lst.stream().filter(e -> e.getUsername().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 5)
                lst = lst.stream().filter(e -> e.getAddress().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 6)
                lst = lst.stream().filter(e -> e.getAvatar().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 7)
                lst = lst.stream().filter(e -> e.getRole().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 8)
                lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 9)
                lst = lst.stream().filter(e -> e.getBlock().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
            else if (index == 10)
                lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
        }
        if (index == 0) return search(lst, obj, obj.getDateOfBirth(), 1);
        else if (index == 1) return search(lst, obj, obj.getEmail(), 2);
        else if (index == 2) return search(lst, obj, obj.getPhoneNumber(), 3);
        else if (index == 3) return search(lst, obj, obj.getUsername(), 4);
        else if (index == 4) return search(lst, obj, obj.getAddress(), 5);
        else if (index == 5) return search(lst, obj, obj.getAvatar(), 6);
        else if (index == 6) return search(lst, obj, obj.getRole(), 7);
        else if (index == 7) return search(lst, obj, obj.getStatus(), 8);
        else if (index == 8) return search(lst, obj, obj.getBlock(), 9);
        else if (index == 9) return search(lst, obj, obj.getDateCreated(), 10);
        else return lst;
    }

//    private List<UserDto> searchByFullName(String[] fullName, List<UserDto> lst) {
//        if (fullName == null) return lst;
//        for (String x : fullName) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getFullName().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByDateOfBirth(String[] dateOfBirth, List<UserDto> lst) {
//        if (dateOfBirth == null) return lst;
//        for (String x : dateOfBirth) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateOfBirth()).contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByPhoneNumber(String[] phoneNumber, List<UserDto> lst) {
//        if (phoneNumber == null) return lst;
//        for (String x : phoneNumber) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByEmail(String[] email, List<UserDto> lst) {
//        if (email == null) return lst;
//        for (String x : email) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getEmail().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByUsername(String[] username, List<UserDto> lst) {
//        if (username == null) return lst;
//        for (String x : username) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getUsername().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByAddress(String[] address, List<UserDto> lst) {
//        if (address == null) return lst;
//        for (String x : address) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getAddress().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByAvatar(String[] avatar, List<UserDto> lst) {
//        if (avatar == null) return lst;
//        for (String x : avatar) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getAvatar().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByRole(String[] role, List<UserDto> lst) {
//        if (role == null) return lst;
//        for (String x : role) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> e.getRole().contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByStatus(String[] status, List<UserDto> lst) {
//        if (status == null) return lst;
//        for (String x : status) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> Boolean.parseBoolean(x) == e.getStatus())
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByBlock(String[] block, List<UserDto> lst) {
//        if (block == null) return lst;
//        for (String x : block) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> Boolean.parseBoolean(x) == e.getBlock())
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
//
//    private List<UserDto> searchByDaetCreate(String[] dateCreated, List<UserDto> lst) {
//        if (dateCreated == null) return lst;
//        for (String x : dateCreated) {
//            if (lst.isEmpty()) return lst;
//            lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x))
//                    .collect(Collectors.toList());
//        }
//        return lst;
//    }
}
