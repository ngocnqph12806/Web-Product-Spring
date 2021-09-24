package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IBrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandApi extends AbstractApi {

    private final IBrandService _iBrandService;
    
    protected BrandApi(HttpServletRequest request, IBrandService iBrandService) {
        super(request);
        _iBrandService = iBrandService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BrandDto> lst = _iBrandService.findAll();
        ResultDto<List<BrandDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iBrandService.findById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<BrandDto> result = new ResultDto<>(OK, null);
        try {
            result.setData(_iBrandService.findById(id));
        } catch (Exception e) {
            result.setData(new BrandDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody BrandDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<BrandDto> result = new ResultDto<>(CREATED, _iBrandService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody BrandDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<BrandDto> result = new ResultDto<>(UPDATED, _iBrandService.update(dto));
        return ResponseEntity.ok(result);
    }

}
