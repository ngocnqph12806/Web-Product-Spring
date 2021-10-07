package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.entity.HistoryEntity;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IHistoryReponsitory;
import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.HistoryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PageDto<List<HistoryVo>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<HistoryEntity> entities = _iHistoryReponsitory.findAll(pageable);
        List<HistoryEntity> lst = entities.getContent();
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(),
                lst.stream().map(e -> (HistoryVo) map(e)).collect(Collectors.toList()));
    }

    @Override
    public HistoryVo findById(String id) {
        Optional<HistoryEntity> optional = _iHistoryReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "history", "history.not.found"));
        return (HistoryVo) map(optional.get());
    }

}
