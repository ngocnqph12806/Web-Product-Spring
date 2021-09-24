package com.example.webproductspringboot.controller.admin;

import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/")
public class UserAdmin {

    @Autowired
    private IUserService _iUserService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("staff")
    public String homeStaff(Model model) {
        System.out.println(CookieUtils.get().errorsProperties(request, "lang", "home"));
        model.addAttribute("lstUser", _iUserService.findAllStaff());
        model.addAttribute("isStaff", true);
        return "page/admin/user/index";
    }

    @GetMapping("visit")
    public String homeVisit(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllVisit());
        model.addAttribute("isStaff", false);
        return "page/admin/user/index";
    }

}
