package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HomeAdmin {

    private final IOrderService _iOrderService;

    public HomeAdmin(IOrderService iOrderService) {
        _iOrderService = iOrderService;
    }

    @GetMapping({"", "/index.html"})
    public String home() {
        return "/index";
    }

    @GetMapping({"load-home"})
    public String loadHome(Model model) {
        List<Long> lstDoanhSo = _iOrderService.findAllDoanhSoTrongNam();
        List<Long> lstDoanhThu = _iOrderService.findAllDoanhThuTrongNam();
        model.addAttribute("lstDoanhSo", lstDoanhSo);
        model.addAttribute("lstDoanhThu", lstDoanhThu);
        return "/load-home";
    }

    @PostMapping({"load-page"})
    public String loadPage() {
        return "load-page";
    }

}
