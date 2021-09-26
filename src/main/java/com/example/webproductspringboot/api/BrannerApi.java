package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IBannerService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBannerVo;
import com.example.webproductspringboot.vo.SearchUserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/banner")
public class BrannerApi extends AbstractApi {

    private final IBannerService _iBannerService;

    protected BrannerApi(HttpServletRequest request, IBannerService iBannerService) {
        super(request);
        _iBannerService = iBannerService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchBannerVo searchBrandVo) {
        List<BannerDto> lst = _iBannerService.findAll();
        lst = search(lst, searchBrandVo, searchBrandVo.getTitle(), 0);
        ResultDto<List<BannerDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathParam("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iBannerService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid BannerDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "banner", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<BannerDto> result = new ResultDto<>(CREATED, _iBannerService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid BannerDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "banner", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<BannerDto> result = new ResultDto<>(UPDATED, _iBannerService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<BannerDto> search(List<BannerDto> lst, SearchBannerVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getTitle().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getPathImage().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getLink().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getPathImage(), 1);
            case 1:
                return search(lst, obj, obj.getLink(), 2);
            case 2:
                return search(lst, obj, obj.getDescription(), 3);
            case 3:
                return search(lst, obj, obj.getStatus(), 4);
            case 4:
                return search(lst, obj, obj.getDateCreated(), 5);
            default:
                return lst;
        }
    }

}
