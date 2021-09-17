package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.CollectionDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
public class CollectionsApi {

    @Autowired
    private ICollectionService _iCollectionService;

    @GetMapping("/{id-collection}")
    public ResponseEntity<?> getColletionById(@PathVariable("id-collection") String idCollection) {
        return ResponseEntity.ok(_iCollectionService.findById(idCollection));
    }

    
    @PostMapping
    public ResponseEntity<?> save( @RequestBody Object dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<Object> result = new ResultDto<>(true, "Lưu thành công", null);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateCollection(@RequestBody CollectionDto dto, Errors errors){
        if(errors.hasErrors()){
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<CollectionDto> result = new ResultDto<>(true, "Đã lưu danh mục", _iCollectionService.save(dto));
        return ResponseEntity.ok(result);
    }

}
