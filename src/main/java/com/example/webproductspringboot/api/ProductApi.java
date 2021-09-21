package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    private IProductService _iProductService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iProductService.findById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<ProductDto> result = new ResultDto<>(true, "", null);
        try {
            result.setData(_iProductService.findById(id));
        } catch (Exception e) {
            result.setData(new ProductDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> save() {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> update() {
        return null;
    }

}
