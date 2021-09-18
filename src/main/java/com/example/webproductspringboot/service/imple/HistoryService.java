package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.vo.HistoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService extends AbstractService  implements IHistoryService {

    @Override
    public List<HistoryVo> findAll() {
        return null;
    }

    @Override
    public HistoryVo findById(String id) {
        return null;
    }

    @Override
    public HistoryVo save(HistoryVo dto) {
        return null;
    }
}
