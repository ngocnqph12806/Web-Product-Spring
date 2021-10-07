package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class InvoiceAdmin {

    @Autowired
    private IInvoiceService _iInvoiceService;

    @PostMapping("invoice/load")
    public String listCollections(@RequestParam("_p") Integer page,
                                  @RequestParam(value = "_s", defaultValue = "10") Integer size,
                                  Model model) {
        PageDto<List<InvoiceDto>> pageDto = _iInvoiceService.findByPage(page, size);
        model.addAttribute("lstInvoice", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-invoice";
    }

}
