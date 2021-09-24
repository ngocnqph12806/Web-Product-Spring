package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IReviewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApi {

    @Autowired
    private IReviewProductService _iReviewProductService;

    @GetMapping
    public ResponseEntity<?> getAll() {
//        List<ReviewDto> lst = _iReviewProductService.findAll();
//        ResultDto<List<ReviewDto>> result = new ResultDto<>(true, "", lst);
//        return ResponseEntity.ok(result);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
//        return ResponseEntity.ok(new ResultDto<>(true, "", _iReviewProductService.findById(id)));
        return null;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReviewDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReviewDto> result = new ResultDto<>(true, "Đã thêm mới đánh giá", _iReviewProductService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ReviewDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReviewDto> result = new ResultDto<>(true, "Đã chỉnh sửa đánh giá", _iReviewProductService.update(dto));
        return ResponseEntity.ok(result);
    }

}
