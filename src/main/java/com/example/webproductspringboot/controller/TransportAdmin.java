package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.TransportDto;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class TransportAdmin {

    @Autowired
    private ITransportService _iTransportService;

    @GetMapping("load/transport")
    public String listCollections(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                                  @RequestParam(value = "_s", defaultValue = "5") Integer size,
                                  Model model) {
        PageDto<List<TransportDto>> pageDto = _iTransportService.findByPage(page, size);
        model.addAttribute("lstTransport", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-transport";
    }

}
