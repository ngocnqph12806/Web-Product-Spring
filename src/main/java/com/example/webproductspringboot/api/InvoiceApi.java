package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.transaction.FormInvoiceAdminDto;
import com.example.webproductspringboot.dto.transaction.IntroInvoiceAdminDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi {

    @Autowired
    private IInvoiceService _iInvoiceService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id-invoice}")
    public ResponseEntity<?> getById(@PathVariable("id-invoice") String id) {
        return ResponseEntity.ok(_iInvoiceService.findIntroById(id));
    }

    @PutMapping
    public ResponseEntity<?> save(@Validated @RequestBody FormInvoiceAdminDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<IntroInvoiceAdminDto> result = new ResultDto<>(true, "Lưu thành công", _iInvoiceService.save(dto));
        return ResponseEntity.ok(result);
    }

}
