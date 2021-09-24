package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnApi {

    @Autowired
    private ICustomersReturnService _iCustomersReturnService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ReturnDto> lst = _iCustomersReturnService.findAll();
        ResultDto<List<ReturnDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iCustomersReturnService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReturnDto> result = new ResultDto<>(true, "Đã thêm mới hoá đơn trả hàng của khách", _iCustomersReturnService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReturnDto> result = new ResultDto<>(true, "Đã chỉnh sửa hoá đơn trả hàng của khách", _iCustomersReturnService.update(dto));
        return ResponseEntity.ok(result);
    }

}
