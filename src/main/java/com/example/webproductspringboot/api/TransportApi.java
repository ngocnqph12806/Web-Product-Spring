package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transports")
public class TransportApi {

    @Autowired
    private ITransportService _iTransportService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TransportDto> lst = _iTransportService.findAll();
        ResultDto<List<TransportDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iTransportService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<TransportDto> result = new ResultDto<>(true, "Đã thêm mới hoá đơn vận chuyển", _iTransportService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<TransportDto> result = new ResultDto<>(true, "Đã chỉnh sửa hoá đơn vận chuyển", _iTransportService.update(dto));
        return ResponseEntity.ok(result);
    }

}
