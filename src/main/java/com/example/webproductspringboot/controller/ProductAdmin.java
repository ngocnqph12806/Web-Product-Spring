package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class ProductAdmin {

    @Autowired
    private IProductService _iProductService;
    @Autowired
    private IBrandService _iBrandService;
    @Autowired
    private ICategoryService _iCategoryService;

    @GetMapping("load/product")
    public String listProduct(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                              @RequestParam(value = "_s", defaultValue = "5") Integer size,
                              Model model) {
        PageDto<List<ProductDto>> pageDto = _iProductService.findByPage(page, size);
        model.addAttribute("lstProduct", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("lstBrands", _iBrandService.findAll());
        model.addAttribute("lstCategories", _iCategoryService.findAll());
        return "/load-product";
    }

    @GetMapping("load/product/form")
    public String formProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("lstBrand", _iBrandService.findAll());
        model.addAttribute("lstCategory", _iCategoryService.findAll());
        return "load-form-product";
    }

    @GetMapping("load/product/form/{id}")
    public String formEditProduct(Model model, @PathVariable("id") String id) {
        try {
            ProductDto productDtoFindById = _iProductService.findProductById(id);
            model.addAttribute("product", productDtoFindById);
            model.addAttribute("lstBrand", _iBrandService.findAll());
            model.addAttribute("lstCategory", _iCategoryService.findAll());
            return "load-form-product";
        } catch (Exception e) {
        }
        return "redirect:/admin/product";
    }

}
