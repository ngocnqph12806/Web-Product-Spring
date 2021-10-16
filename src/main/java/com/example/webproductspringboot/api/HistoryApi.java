package com.example.webproductspringboot.api;

import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.vo.HistoryVo;
import com.example.webproductspringboot.vo.SearchHistoryVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/history")
public class HistoryApi extends AbstractApi {

    private final IHistoryService _iHistoryService;

    protected HistoryApi(HttpServletRequest request, HttpServletResponse response, IHistoryService iHistoryService) {
        super(request, response);
        _iHistoryService = iHistoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchHistoryVo searchHistoryVo) {
        List<HistoryVo> lst = _iHistoryService.findAll();
        lst = search(lst, searchHistoryVo, searchHistoryVo.getIdStaff(), 0);
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(_iHistoryService.findById(id));
    }

    private List<HistoryVo> search(List<HistoryVo> lst, SearchHistoryVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getNameStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getNameStaff(), 1);
            case 1:
                return search(lst, obj, obj.getDescription(), 2);
            case 2:
                return search(lst, obj, obj.getDateCreated(), 3);
            default:
                return lst;
        }
    }

}
