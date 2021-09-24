package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.entity.HistoryEntity;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IHistoryReponsitory;
import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.vo.HistoryVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryService extends AbstractService implements IHistoryService {

    private final IHistoryReponsitory _iHistoryReponsitory;

    protected HistoryService(HttpServletRequest request, IHistoryReponsitory iHistoryReponsitory) {
        super(request);
        _iHistoryReponsitory = iHistoryReponsitory;
    }

    @Override
    public List<HistoryVo> findAll() {
        List<HistoryEntity> lst = _iHistoryReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (HistoryVo) map(e)).collect(Collectors.toList());
    }

    @Override
    public HistoryVo findById(String id) {
        Optional<HistoryEntity> optional = _iHistoryReponsitory.findById(id);
        if(optional.isEmpty()) throw new NotFoundException("Lịch sử thao tác không tồn tại");
        return (HistoryVo) map(optional.get());
    }

}
