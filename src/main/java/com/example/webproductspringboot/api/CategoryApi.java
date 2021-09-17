package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {

    @Autowired
    private ICategoryService _iCategoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-category}")
    public ResponseEntity<?> getById(@PathVariable("id-category") String id) {
        return ResponseEntity.ok(_iCategoryService.findById(id));
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
    public ResponseEntity<?> update(@Validated @RequestBody CategoryDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<CategoryDto> result = new ResultDto<>(true, "Lưu thành công", _iCategoryService.save(dto));
        return ResponseEntity.ok(result);
    }

}
