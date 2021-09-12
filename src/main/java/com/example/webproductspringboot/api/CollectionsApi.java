package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.product.IntroCollectionAdminDto;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collections")
public class CollectionsApi {

    @Autowired
    private ICollectionService _iCollectionService;

    @GetMapping("/{id-collection}")
    public ResponseEntity<?> getColletionById(@PathVariable("id-collection") String idCollection) {
        return ResponseEntity.ok(_iCollectionService.findIntroCollectionById(idCollection));
    }

}
