package com.example.webproductspringboot.controller.admin;

import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/")
public class UserAdmin {

    private final IUserService _iUserService;

    public UserAdmin(IUserService iUserService) {
        _iUserService = iUserService;
    }

    @GetMapping("staff")
    public String homeStaff(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllIntroStaff());
        model.addAttribute("isStaff", true);
        return "page/admin/person/index";
    }

    @GetMapping("visit")
    public String homeVisit(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllIntroVisit());
        model.addAttribute("isStaff", false);
        return "page/admin/person/index";
    }

}
