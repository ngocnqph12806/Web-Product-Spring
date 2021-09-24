package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.vo.HistoryVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryApi  extends AbstractApi {

    private final IHistoryService _iHistoryService;

    protected HistoryApi(HttpServletRequest request, IHistoryService iHistoryService) {
        super(request);
        _iHistoryService = iHistoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<HistoryVo> lst = _iHistoryService.findAll();
        ResultDto<List<HistoryVo>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iHistoryService.findById(id)));
    }

}
