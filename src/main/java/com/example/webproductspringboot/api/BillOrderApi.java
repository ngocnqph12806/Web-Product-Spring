package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.transaction.FormOrderAdminDto;
import com.example.webproductspringboot.dto.transaction.IntroOrderAdminDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class BillOrderApi {

    @Autowired
    private IOrderService _iOrderService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-bill-order}")
    public ResponseEntity<?> getById(@PathVariable("id-bill-order") String id) {
        return ResponseEntity.ok(_iOrderService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormOrderAdminDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroOrderAdminDto> result = new ResultDto<>(true, "Lưu thành công", _iOrderService.saveOrder(dto));
        return ResponseEntity.ok(result);
    }

}
