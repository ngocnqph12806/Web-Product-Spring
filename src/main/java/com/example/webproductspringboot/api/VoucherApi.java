package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.partner.FormVoucherDto;
import com.example.webproductspringboot.dto.partner.IntroVoucherDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voucher")
public class VoucherApi {

    @Autowired
    private IVoucherService _iVoucherService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-voucher}")
    public ResponseEntity<?> getById(@PathVariable("id-voucher") String id) {
        return ResponseEntity.ok(_iVoucherService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormVoucherDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroVoucherDto> result = new ResultDto<>(true, "Lưu thành công", _iVoucherService.save(dto));
        return ResponseEntity.ok(result);
    }

}
