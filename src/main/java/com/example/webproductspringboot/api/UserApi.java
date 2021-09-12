package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.staff.FormUserAdminDto;
import com.example.webproductspringboot.dto.staff.IntroStaffAdminDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private IUserService _iUserService;

    @GetMapping(path = {"/{user-name}"})
    public ResponseEntity<?> getByUserName(@PathVariable(name = "user-name", value = "") String userNameStaff,
                                           @RequestParam(name = "modal", defaultValue = "") String getForModal) {
        if (getForModal != null && !getForModal.isEmpty() && !getForModal.isBlank() && getForModal.equals("true")) {
            try {
                return ResponseEntity.ok(_iUserService.findByUserName(userNameStaff));
            } catch (Exception e) {
                return ResponseEntity.ok(new FormUserAdminDto());
            }
        } else {
            return ResponseEntity.ok(_iUserService.findByUserName(userNameStaff));
        }
    }

    @PutMapping
    public ResponseEntity<?> saveStaff(@RequestBody @Valid FormUserAdminDto formUserAdminDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroStaffAdminDto> result = new ResultDto<>(true, "Lưu thành công", _iUserService.saveInfoStaff(formUserAdminDto));
        return ResponseEntity.ok(result);
    }

    // thay đổi status và block
    @PostMapping({"/{actions}/{user-name}"})
    public ResponseEntity<?> changeStaff(@PathVariable(name = "actions") String requestActions, @PathVariable(name = "user-name") String userNameStaff) {
        ResultDto result = new ResultDto<>();
        result.setResult(false);
        result.setMessage("Dữ liệu không hợp lệ");
        if (requestActions.equals("block")) {
            if (_iUserService.changeBlockStaff(userNameStaff)) {
                result.setMessage("Đã khoá tài khản " + userNameStaff);
                result.setResult(true);
            } else {
                result.setMessage("Đã mở khoá tài khản " + userNameStaff);
                result.setResult(true);
            }
        } else if (requestActions.equals("status")) {
            if (_iUserService.changeStatusStaff(userNameStaff)) {
                result.setMessage("Tài khoản " + userNameStaff + " đã được kích hoạt");
                result.setResult(true);
            } else {
                result.setMessage("Kích hoạt thất bại");
            }
        } else {
            throw new NotFoundException("Lỗi đường dẫn");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
