package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.product.FormCategoryAdminDto;
import com.example.webproductspringboot.dto.product.IntroCategoryAdminDto;
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
        return ResponseEntity.ok(_iCategoryService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormCategoryAdminDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroCategoryAdminDto> result = new ResultDto<>(true, "Lưu thành công", _iCategoryService.save(dto));
        return ResponseEntity.ok(result);
    }

}
