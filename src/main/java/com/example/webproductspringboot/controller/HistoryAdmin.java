package com.example.webproductspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class HistoryAdmin {

    @PostMapping("history/load")
    public String listHistory(Model model) {
//        model.addAttribute("lstStaff", _iVisitService.findAll());
        return "/website/history";
    }

}
