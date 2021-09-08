package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.InfoStaffDto;
import com.example.webproductspringboot.dto.RequestCommand;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestAPI {

    @Autowired
    private IStaffService _iStaffService;
    @Autowired
    private IProductService _iProductService;

    @GetMapping("/staff")
    public ResponseEntity<?> getInfo(){
        return ResponseEntity.ok(_iStaffService.getInfo());
    }

    @PutMapping("/staff")
    public ResponseEntity<?> login(@RequestBody RequestCommand<InfoStaffDto> request){
        System.out.println(request);
        System.out.println(request.getData().getClass());

        return ResponseEntity.ok(_iStaffService.getInfo());
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok(_iProductService.findBestSallers());
    }


}
