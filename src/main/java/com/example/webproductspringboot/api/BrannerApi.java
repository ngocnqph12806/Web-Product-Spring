package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BrannerApi {

    @Autowired
    private IBannerService _iBannerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BannerDto> lst = _iBannerService.findAll();
        ResultDto<List<BannerDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathParam("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iBannerService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid BannerDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<BannerDto> result = new ResultDto<>(true, "Lưu thành công", _iBannerService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid BannerDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<BannerDto> result = new ResultDto<>(true, "Lưu thành công", _iBannerService.update(dto));
        return ResponseEntity.ok(result);
    }

}
