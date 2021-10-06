package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/")
public class ProductAdmin {

    @Autowired
    private IProductService _iProductService;
    @Autowired
    private IBrandService _iBrandService;
    @Autowired
    private ICategoryService _iCategoryService;

    @GetMapping("product")
    public String listProduct(Model model) {
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        model.addAttribute("lstBrands", _iBrandService.findAll());
        model.addAttribute("lstCategories", _iCategoryService.findAll());
        return "/product/index-product";
    }

    @GetMapping(value = "product/edit", params = "id")
    public String editWithId(@RequestParam("id") String id, RedirectAttributes ra) {
        try {
            ra.addFlashAttribute("product", _iProductService.findProductById(id));
        } catch (Exception e) {
            ra.addFlashAttribute("product", new ProductDto());
        }
        return "redirect:/admin/product/edit";
    }

    @GetMapping(value = {"product/edit", "product/add"})
    public String formProduct(Model model) {
        if (model.getAttribute("product") == null) {
            model.addAttribute("product", new ProductDto());
        }
        model.addAttribute("lstBrands", _iBrandService.findAll());
        model.addAttribute("lstCategories", _iCategoryService.findAll());
        return "/product/form-product";
    }

}
