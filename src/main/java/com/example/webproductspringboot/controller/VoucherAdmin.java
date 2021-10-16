package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.VoucherDto;
import com.example.webproductspringboot.service.intf.IVoucherService;
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
public class VoucherAdmin {

    @Autowired
    private IVoucherService _iVoucherService;

    @GetMapping("load/voucher")
    public String listVoucher(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                              @RequestParam(value = "_s", defaultValue = "5") Integer size,
                              Model model) {
        PageDto<List<VoucherDto>> pageDto = _iVoucherService.findByPage(page, size);
        model.addAttribute("lstVoucher", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-voucher";
    }

}
