package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class OrderAdmin {

    @Autowired
    private IOrderService _iOrderService;

    @PostMapping("bill-order/load")
    public String listCollections(@RequestParam("_p") Integer page,
                                  @RequestParam(value = "_s", defaultValue = "10") Integer size,
                                  Model model) {
        PageDto<List<OrderDto>> pageDto = _iOrderService.findByPage(page, size);
        model.addAttribute("lstOrder", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-order";
    }

}
