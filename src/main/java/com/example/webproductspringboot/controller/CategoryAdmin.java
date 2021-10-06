package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class CategoryAdmin {

    @Autowired
    private ICollectionService _iCollectionService;
    @Autowired
    private ICategoryService _iCategoryService;
    @Autowired
    private IBrandService _iBrandService;

    @PostMapping("category/load")
    public String loadCategory(Model model) {
        model.addAttribute("lstCategoriesAndBrand", _iCategoryService.findAll());
        model.addAttribute("lstCollections", _iCollectionService.findAll());
        model.addAttribute("isCategory", true);
        return "/product/load-not-product";
    }

    @PostMapping("brand/load")
    public String loadBrand(Model model) {
        model.addAttribute("lstCategoriesAndBrand", _iBrandService.findAll());
        model.addAttribute("lstCollections", _iCollectionService.findAll());
        model.addAttribute("isCategory", false);
        return "/product/load-not-product";
    }

}
