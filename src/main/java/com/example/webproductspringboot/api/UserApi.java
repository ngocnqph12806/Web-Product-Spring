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
        System.out.println(searchUserVo);
        List<UserDto> lst = _iUserService.findAll();
        System.out.println(searchUserVo);
        lst = search(lst, searchUserVo, searchUserVo.getFullName(), 0);
//        ResultDto<List<UserDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(lst);
    }

    @GetMapping(params = "_type")
    public ResponseEntity<?> getAll(@RequestParam("_type") String typeUser) {
        if (typeUser.equals("admin")) {
            return ResponseEntity.ok(_iUserService.findAllStaff());
        }
        return ResponseEntity.ok(_iUserService.findAllVisit());
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

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody ChangeUserDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", errors.getFieldErrors().get(0).getDefaultMessage()));
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
}
