package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.partner.FormTransportDto;
import com.example.webproductspringboot.dto.partner.IntroTransprotDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transports")
public class TransportApi {

    @Autowired
    private ITransportService _iTransportService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-transport}")
    public ResponseEntity<?> getById(@PathVariable("id-transport") String id) {
        return ResponseEntity.ok(_iTransportService.findIntroById(id));
    }

    
    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Object dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<Object> result = new ResultDto<>(true, "Lưu thành công", null);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody FormTransportDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroTransprotDto> result = new ResultDto<>(true, "Lưu thành công", _iTransportService.save(dto));
        return ResponseEntity.ok(result);
    }

}
