package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IBannerService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BrannerApi extends AbstractApi {

    private final IBannerService _iBannerService;

    protected BrannerApi(HttpServletRequest request, IBannerService iBannerService) {
        super(request);
        _iBannerService = iBannerService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BannerDto> lst = _iBannerService.findAll();
        ResultDto<List<BannerDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathParam("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iBannerService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid BannerDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<BannerDto> result = new ResultDto<>(CREATED, _iBannerService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid BannerDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        if (!dto.getId().equals(id)) throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<BannerDto> result = new ResultDto<>(UPDATED, _iBannerService.update(dto));
        return ResponseEntity.ok(result);
    }

}
