package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.TransportDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transports")
public class TransportApi {

    @Autowired
    private ITransportService _iTransportService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<TransportDto> result = new ResultDto<>(true, "Lưu thành công", _iTransportService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<TransportDto> result = new ResultDto<>(true, "Lưu thành công", _iTransportService.update(dto));
        return ResponseEntity.ok(result);
    }

}
