package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.product.FormProductDto;
import com.example.webproductspringboot.dto.product.IntroProductDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

    @Autowired
    private IProductService _iProductService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-product}")
    public ResponseEntity<?> getById(@PathVariable("id-product") String id) {
        return ResponseEntity.ok(_iProductService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormProductDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroProductDto> result = new ResultDto<>(true, "Lưu thành công", _iProductService.save(dto));
        return ResponseEntity.ok(result);
    }

}
