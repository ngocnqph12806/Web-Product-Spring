package com.example.webproductspringboot.api;

import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestAPI {

    @Autowired
    private IUserService _iUserService;
    @Autowired
    private IProductService _iProductService;

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok(_iProductService.findBestSallers());
    }


}
