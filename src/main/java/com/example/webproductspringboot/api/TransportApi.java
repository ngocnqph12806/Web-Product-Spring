package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ITransportService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBannerVo;
import com.example.webproductspringboot.vo.SearchTransportVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transports")
public class TransportApi extends AbstractApi {

    private final ITransportService _iTransportService;

    protected TransportApi(HttpServletRequest request, ITransportService iTransportService) {
        super(request);
        _iTransportService = iTransportService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchTransportVo searchTransportVo) {
        List<TransportDto> lst = _iTransportService.findAll();
        lst = search(lst, searchTransportVo, searchTransportVo.getIdOrder(), 0);
        ResultDto<List<TransportDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iTransportService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "transport", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<TransportDto> result = new ResultDto<>(CREATED, _iTransportService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid TransportDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "transport", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<TransportDto> result = new ResultDto<>(UPDATED, _iTransportService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<TransportDto> search(List<TransportDto> lst, SearchTransportVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdOrder().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                case 1:
                    lst = lst.stream().filter(e -> e.getFullName().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                case 2:
                    lst = lst.stream().filter(e -> e.getPhoneNumber().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                case 3:
                    lst = lst.stream().filter(e -> e.getAddress().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                case 4:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                case 5:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                case 6:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getFullName(), index + 1);
            case 1:
                return search(lst, obj, obj.getPhoneNumber(), index + 1);
            case 2:
                return search(lst, obj, obj.getAddress(), index + 1);
            case 3:
                return search(lst, obj, obj.getDescription(), index + 1);
            case 4:
                return search(lst, obj, obj.getStatus(), index + 1);
            case 5:
                return search(lst, obj, obj.getDateCreated(), index + 1);
            default:
                return lst;
        }
    }

}
