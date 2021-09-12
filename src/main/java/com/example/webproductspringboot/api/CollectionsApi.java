package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.product.IntroCollectionAdminDto;
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
        return ResponseEntity.ok(_iCollectionService.findIntroCollectionById(idCollection));
    }

    @PutMapping
    public ResponseEntity<?> saveCollection(@RequestBody IntroCollectionAdminDto dto, Errors errors){
        System.out.println(dto);
        if(errors.hasErrors()){
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroCollectionAdminDto> result = new ResultDto<>(true, "Đã lưu danh mục", _iCollectionService.saveCollection(dto));
        return ResponseEntity.ok(result);
    }

}
