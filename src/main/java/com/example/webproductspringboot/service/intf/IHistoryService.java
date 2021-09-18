package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.vo.HistoryVo;

import java.util.List;

public interface IHistoryService {

    List<HistoryVo> findAll();
    
    HistoryVo findById(String id);
    
    HistoryVo save(HistoryVo vo);

}
