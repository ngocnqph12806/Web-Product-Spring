package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.vo.HistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryApi {

    @Autowired
    private IHistoryService _iHistoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<HistoryVo> lst = _iHistoryService.findAll();
        ResultDto<List<HistoryVo>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iHistoryService.findById(id)));
    }

}
