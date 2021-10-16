package com.example.webproductspringboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountAdmin {

    @GetMapping("/login")
    public String login(@RequestParam(value = "message", defaultValue = "") String message,
                        Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        if (!authentication.getName().isBlank()
                && !authentication.getName().isEmpty()
                && !authentication.getName().equals("anonymousUser")) {
            return "redirect:/admin";
        }
        model.addAttribute("message", "");
        if (message != null) {
            if (message.equals("max_session")) {
                model.addAttribute("message", "Tài khoản đã đăng nhập ở một máy khác.");
            } else if (message.equals("error")) {
                model.addAttribute("message", "Đăng nhập thất bại");
            }
        }
        return "/account/signin";
    }

    @GetMapping("/signout")
    public String signout() {
        return "/account/logout";
    }

}
