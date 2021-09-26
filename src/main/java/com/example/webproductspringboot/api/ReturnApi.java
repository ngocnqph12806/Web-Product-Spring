package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBannerVo;
import com.example.webproductspringboot.vo.SearchReturnVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/returns")
public class ReturnApi extends AbstractApi {

    private final ICustomersReturnService _iCustomersReturnService;

    protected ReturnApi(HttpServletRequest request, ICustomersReturnService iCustomersReturnService) {
        super(request);
        _iCustomersReturnService = iCustomersReturnService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchReturnVo searchReturnVo) {
        List<ReturnDto> lst = _iCustomersReturnService.findAll();
        lst = search(lst, searchReturnVo, searchReturnVo.getIdOrder(), 0);
        ResultDto<List<ReturnDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iCustomersReturnService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<ReturnDto> result = new ResultDto<>(CREATED, _iCustomersReturnService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<ReturnDto> result = new ResultDto<>(UPDATED, _iCustomersReturnService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<ReturnDto> search(List<ReturnDto> lst, SearchReturnVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdOrder().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateOrder()).contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getIdUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getNameUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getIdStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getNameStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 7:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 8:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getDateOrder(), 1);
            case 1:
                return search(lst, obj, obj.getIdUser(), 2);
            case 2:
                return search(lst, obj, obj.getNameUser(), 3);
            case 3:
                return search(lst, obj, obj.getIdStaff(), 4);
            case 4:
                return search(lst, obj, obj.getNameStaff(), 5);
            case 5:
                return search(lst, obj, obj.getDescription(), 6);
            case 6:
                return search(lst, obj, obj.getStatus(), 7);
            case 7:
                return search(lst, obj, obj.getDateCreated(), 8);
            default:
                return lst;
        }
    }

}
