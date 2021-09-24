package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi extends AbstractApi {

    private final ICategoryService _iCategoryService;

    protected CategoryApi(HttpServletRequest request, ICategoryService iCategoryService) {
        super(request);
        _iCategoryService = iCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iCategoryService.findById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<ResultDto<CategoryDto>> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<CategoryDto> result = new ResultDto<>(OK, null);
        try {
            result.setData(_iCategoryService.findById(id));
        } catch (Exception e) {
            result.setData(new CategoryDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody CategoryDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<CategoryDto> result = new ResultDto<>(CREATED, _iCategoryService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody CategoryDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<CategoryDto> result = new ResultDto<>(UPDATED, _iCategoryService.update(dto));
        return ResponseEntity.ok(result);
    }

}
