package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherApi {

    @Autowired
    private IVoucherService _iVoucherService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<VoucherDto> lst = _iVoucherService.findAll();
        ResultDto<List<VoucherDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iVoucherService.findById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<VoucherDto> result = new ResultDto<>(true, "", null);
        try {
            result.setData(_iVoucherService.findById(id));
        } catch (Exception e) {
            result.setData(new VoucherDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody VoucherDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<VoucherDto> result = new ResultDto<>(true, "Đã thêm mới mã giảm giá", _iVoucherService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody VoucherDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<VoucherDto> result = new ResultDto<>(true, "Đã chỉnh sửa mã giảm giá", _iVoucherService.update(dto));
        return ResponseEntity.ok(result);
    }

}
