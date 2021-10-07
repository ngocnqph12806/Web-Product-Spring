package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.vo.HistoryVo;

import java.util.List;

public interface IHistoryService {

    List<HistoryVo> findAll();

    PageDto<List<HistoryVo>> findByPage(Integer page, Integer size);

    HistoryVo findById(String id);

}
