package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.transaction.FormReturnProductDto;
import com.example.webproductspringboot.dto.transaction.IntroReturnProductDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/return-products")
public class ReturnProductApi {

    @Autowired
    private ICustomersReturnService _iCustomersReturnService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-return-product}")
    public ResponseEntity<?> getById(@PathVariable("id-return-product") String id) {
        return ResponseEntity.ok(_iCustomersReturnService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormReturnProductDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroReturnProductDto> result = new ResultDto<>(true, "Lưu thành công", _iCustomersReturnService.save(dto));
        return ResponseEntity.ok(result);
    }

}
