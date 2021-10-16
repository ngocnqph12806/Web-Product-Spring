package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.CollectionDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICollectionService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchCollectionVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/collections")
public class CollectionsApi extends AbstractApi {

    private final ICollectionService _iCollectionService;

    protected CollectionsApi(HttpServletRequest request, HttpServletResponse response, ICollectionService iCollectionService) {
        super(request, response);
        _iCollectionService = iCollectionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchCollectionVo searchCollectionVo) {
        List<CollectionDto> lst = _iCollectionService.findAll();
        lst = search(lst, searchCollectionVo, searchCollectionVo.getName(), 0);
        ResultDto<List<CollectionDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColletionById(@PathVariable("id") String idCollection) {
        return ResponseEntity.ok(_iCollectionService.findById(idCollection));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getColletionByIdWithModal(@PathVariable("id") String idCollection) {
        try {
            return ResponseEntity.ok(_iCollectionService.findById(idCollection));
        } catch (Exception e) {
        }
        return ResponseEntity.ok(new CollectionDto());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CollectionDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "collection", errors.getFieldErrors().get(0).getDefaultMessage()));
        }
        ResultDto<CollectionDto> result = new ResultDto<>(CREATED, _iCollectionService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCollection(@PathVariable("id") String id,
                                              @RequestBody CollectionDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors()) {
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "collection", errors.getFieldErrors().get(0).getDefaultMessage()));
        }
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<CollectionDto> result = new ResultDto<>(UPDATED, _iCollectionService.updare(dto));
        return ResponseEntity.ok(result);
    }

    private List<CollectionDto> search(List<CollectionDto> lst, SearchCollectionVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getName().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getCountProducts().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getDescription(), 1);
            case 1:
                return search(lst, obj, obj.getStatus(), 2);
            case 2:
                return search(lst, obj, obj.getDateCreated(), 3);
            case 3:
                return search(lst, obj, obj.getCountProducts(), 4);
            default:
                return lst;
        }
    }

}
