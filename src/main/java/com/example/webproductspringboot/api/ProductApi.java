package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.RequestActions;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.product.FormProductDto;
import com.example.webproductspringboot.dto.product.IntroProductAdminDto;
import com.example.webproductspringboot.dto.product.IntroProductWebDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
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

    @PutMapping("/{id}")
    public ResponseEntity<?> changeById(@PathVariable("id") String id,
                                        @RequestBody RequestActions requestActions,
                                        @RequestBody FormProductDto formProductDto) {
        System.out.println(requestActions);
        if (id != null && !id.isBlank() && !id.isEmpty()) {
            if (requestActions.getStatus() != null) {
                if (requestActions.getStatus().equals("true") || requestActions.getStatus().equals("false")) {
                    if (_iProductService.changeStatus(id, Boolean.parseBoolean(requestActions.getStatus()))) {
                        return ResponseEntity.ok(new ResultDto<>().builder().result(true).message("Đã thay đổi trạng thái").build());
                    } else {
                        return ResponseEntity.ok(new ResultDto<>().builder().result(false).message("Chưa thay đổi trạng thái").build());
                    }
                }
                throw new InternalServerException("Dữ liệu chỉ nhận true hoặc false.");
            }
        }
        throw new BadRequestException("Lỗi ID không tồn tại.");
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
    public ResponseEntity<?> update(@Validated @RequestBody FormProductDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroProductWebDto> result = new ResultDto<>(true, "Lưu thành công", _iProductService.save(dto));
        return ResponseEntity.ok(result);
    }

}
