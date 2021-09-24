package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.CollectionDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/collections")
public class CollectionsApi extends AbstractApi {

    private final ICollectionService _iCollectionService;

    protected CollectionsApi(HttpServletRequest request, ICollectionService iCollectionService) {
        super(request);
        _iCollectionService = iCollectionService;
    }

    @GetMapping("/{id-collection}")
    public ResponseEntity<?> getColletionById(@PathVariable("id-collection") String idCollection) {
        ResultDto<CollectionDto> result = new ResultDto<>(OK, _iCollectionService.findById(idCollection));
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody CollectionDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<CollectionDto> result = new ResultDto<>(CREATED, _iCollectionService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCollection(@PathVariable("id") String id,
                                              @RequestBody CollectionDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<CollectionDto> result = new ResultDto<>(UPDATED, _iCollectionService.updare(dto));
        return ResponseEntity.ok(result);
    }

}
