package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    @Autowired
    private IOrderService _iOrderService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<OrderDto> lst = _iOrderService.findAll();
        ResultDto<List<OrderDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iOrderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<OrderDto> result = new ResultDto<>(true, "Đã thêm mới hoá đơn đặt hàng", _iOrderService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<OrderDto> result = new ResultDto<>(true, "Đã chỉnh sửa hoá đơn đặt hàng", _iOrderService.update(dto));
        return ResponseEntity.ok(result);
    }

}
