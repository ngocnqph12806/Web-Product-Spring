package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IHistoryService;
import com.example.webproductspringboot.vo.HistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class HistoryAdmin {

    @Autowired
    private IHistoryService _iHistoryService;

    @PostMapping("history/load")
    public String listHistory(@RequestParam("_p") Integer page,
                              @RequestParam(value = "_s", defaultValue = "10") Integer size,
                              Model model) {
        System.out.println(page);
        PageDto<List<HistoryVo>> pageDto = _iHistoryService.findByPage(page, size);
        model.addAttribute("lstHistory", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "load-history";
    }

}
