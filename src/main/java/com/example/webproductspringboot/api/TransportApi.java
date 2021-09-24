package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transports")
public class TransportApi extends AbstractApi {

    private final ITransportService _iTransportService;

    protected TransportApi(HttpServletRequest request, ITransportService iTransportService) {
        super(request);
        _iTransportService = iTransportService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TransportDto> lst = _iTransportService.findAll();
        ResultDto<List<TransportDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iTransportService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<TransportDto> result = new ResultDto<>(CREATED, _iTransportService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<TransportDto> result = new ResultDto<>(UPDATED, _iTransportService.update(dto));
        return ResponseEntity.ok(result);
    }

}
