package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.partner.FormBrandAdminDto;
import com.example.webproductspringboot.dto.partner.IntroBrandAdminDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class BrandApi {

    @Autowired
    private IBrandService _iBrandService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-brand}")
    public ResponseEntity<?> getById(@PathVariable("id-brand") String id) {
        return ResponseEntity.ok(_iBrandService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormBrandAdminDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroBrandAdminDto> result = new ResultDto<>(true, "Lưu thành công", _iBrandService.save(dto));
        return ResponseEntity.ok(result);
    }

}
