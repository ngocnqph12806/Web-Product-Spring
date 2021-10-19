package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IConfirmService;
import com.example.webproductspringboot.service.intf.ISendmailService;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.ContainsUtils;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserApi extends AbstractApi {

    private final IUserService _iUserService;
    private final ISendmailService _iSendmailService;
    private final IConfirmService _iConfirmService;

    protected UserApi(HttpServletRequest request, HttpServletResponse response, IUserService iUserService, ISendmailService iSendmailService, IConfirmService iConfirmService) {
        super(request, response);
        _iUserService = iUserService;
        _iSendmailService = iSendmailService;
        _iConfirmService = iConfirmService;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAll(SearchUserVo searchUserVo) {
        List<UserDto> lst = _iUserService.findAll();
        lst = search(lst, searchUserVo, searchUserVo.getFullName(), 0);
//        ResultDto<List<UserDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(lst);
    }

    @GetMapping(params = "_type")
    public ResponseEntity<?> getAll(@RequestParam("_type") String typeUser) {
        if (typeUser.equals("staff")) {
            return ResponseEntity.ok(_iUserService.findAllStaff());
        }
        return ResponseEntity.ok(_iUserService.findAllVisit());
    }

    @GetMapping(path = "/get-info-checkout", params = "id")
    public ResponseEntity<?> getInfoCheckoutByUserLogin(@RequestParam("id") String id) {
        return ResponseEntity.ok(_iUserService.getInfoCheckoutByUserLogin(id));
    }

    @GetMapping(path = "/get-id-by-user-login", params = "username")
    public ResponseEntity<?> getIdByUserName(@RequestParam("username") String username) {
        return ResponseEntity.ok(_iUserService.getIdByUserName(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
//        ResultDto<UserDto> result = new ResultDto<>(OK, _iUserService.findById(id));
        return ResponseEntity.ok(_iUserService.findById(id));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(_iUserService.findById(id));
        } catch (Exception e) {
        }
        return ResponseEntity.ok(new UserDto());
    }

    @GetMapping(value = "/{id}", params = "register_Code")
    public void confirmUser(@PathVariable("id") String id,
                            @RequestParam("register_Code") Integer register_Code,
                            @RequestParam("url_Success") String url_Success) throws IOException {
        UserDto userDtoFindById = _iUserService.findById(id);
        if (!userDtoFindById.getStatus() && _iConfirmService.put(register_Code, ContainsUtils.CONFIRM_REGISTER)) {
            _iUserService.confirmUser(id);
            response.sendRedirect(url_Success);
        } else {
            int code = (int) (Math.random() * 9999999);
            String message = "<a href=\"http://localhost:8091/api/users/" + userDtoFindById.getId()
                    + "?register_Code=" + code
                    + "&url_Success=" + url_Success
                    + "\">Xác nhận tài khoản</a>";
            _iSendmailService.push(userDtoFindById.getEmail(), "Đăng ký tài khoản", message);
            _iConfirmService.push(code, userDtoFindById, ContainsUtils.CONFIRM_REGISTER);
        }
        throw new NotFoundException(CookieUtils.get().errorsProperties(request, "lang", "page.not.found"));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody UserDto dto, Errors errors) {
        if (errors.hasErrors()){
            System.out.println(errors.getFieldErrors().get(0).getDefaultMessage());
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", errors.getFieldErrors().get(0).getDefaultMessage()));
        }
//        ResultDto<UserDto> result = new ResultDto<>(CREATED, _iUserService.save(dto));
        return ResponseEntity.ok(_iUserService.save(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveRegister(@Validated @RequestBody UserRegisterDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", errors.getFieldErrors().get(0).getDefaultMessage()));
        UserDto userDto = _iUserService.saveRegister(dto);
        int code = (int) (Math.random() * 9999999);
        String message = "<a href=\"http://localhost:8091/api/users/" + userDto.getId()
                + "?register_Code=" + code
                + "&url_Success=" + dto.getUrlClient()
                + "\">Xác nhận tài khoản</a>";
        _iSendmailService.push(userDto.getEmail(), "Đăng ký tài khoản", message);
        _iConfirmService.push(code, userDto, ContainsUtils.CONFIRM_REGISTER);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid UserDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors()) {
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", errors.getFieldErrors().get(0).getDefaultMessage()));
        }
//        ResultDto<UserDto> result = new ResultDto<UserDto>(UPDATED, _iUserService.update(dto));
        return ResponseEntity.ok(_iUserService.update(dto));
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
