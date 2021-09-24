package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IReviewProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApi  extends AbstractApi{

    private final IReviewProductService _iReviewProductService;

    protected ReviewApi(HttpServletRequest request, IReviewProductService iReviewProductService) {
        super(request);
        _iReviewProductService = iReviewProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
//        List<ReviewDto> lst = _iReviewProductService.findAll();
//        ResultDto<List<ReviewDto>> result = new ResultDto<>(HttpCodeApi.OK, lst);
//        return ResponseEntity.ok(result);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
//        return ResponseEntity.ok(new ResultDto<>(HttpCodeApi.OK, _iReviewProductService.findById(id)));
        return null;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReviewDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReviewDto> result = new ResultDto<>(CREATED, _iReviewProductService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ReviewDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReviewDto> result = new ResultDto<>(UPDATED, _iReviewProductService.update(dto));
        return ResponseEntity.ok(result);
    }

}
