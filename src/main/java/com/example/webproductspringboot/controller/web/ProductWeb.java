package com.example.webproductspringboot.controller.web;

import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductWeb {

    @Autowired
    private IProductService _iProductService;
    @Autowired
    private ICategoryService _iCategoryService;

    @GetMapping("/category/{id}/{path-url}")
    public String getByCategory(@PathVariable("id") String idCategory, @PathVariable("path-url") String pathUrl, Model model) {
        if (idCategory != null && !idCategory.isEmpty() && !idCategory.isBlank()
                && pathUrl != null && !pathUrl.isEmpty() && !pathUrl.isBlank()) {
            try {
//                SearchCategoryDto searchCategoryDto = _iCategoryService.findByIdAndPath(idCategory, pathUrl);
//                if (searchCategoryDto != null) {
//                    model.addAttribute("listProduct", _iProductService.findByIdCategory(searchCategoryDto.getIdCategory()));
//                    model.addAttribute("title", searchCategoryDto.getName());
//                    model.addAttribute("filterCategoryProduct", _iProductService.getSetCategoryDetailsByIdCategory(searchCategoryDto.getIdCategory()));
//                    model.addAttribute("filterColorProduct", _iProductService.getSetColorByIdCategory(searchCategoryDto.getIdCategory()));
//                    model.addAttribute("filterPriceProduct", _iProductService.getMinMaxPrice());
//                    return "page/web/product/list-product";
//                }
            } catch (Exception e) {
                e.printStackTrace();
                return showList(model);
            }
        }
        return showList(model);
    }

    @GetMapping("/product/{id-url}/{path-url}")
    public String detailsProduct(@PathVariable("id-url") String idUrl, @PathVariable("path-url") String pathUrl, Model model) {
        if (pathUrl != null && !pathUrl.isEmpty() && !pathUrl.isBlank()
                && idUrl != null && !idUrl.isEmpty() && !idUrl.isBlank()
                && pathUrl.endsWith(".html")) {
            try {
//                DetailsProductDto searchProductDto = _iProductService.findByPath(Long.parseLong(idUrl), pathUrl.substring(0, pathUrl.lastIndexOf(".html")));
//                if (searchProductDto != null) {
//                    model.addAttribute("product", searchProductDto);
//                    return "page/web/product/details-product";
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/product";
    }

    @GetMapping("/product")
    public String showList(Model model) {
        model.addAttribute("listProduct", _iProductService.findAllProduct());
        model.addAttribute("title", "Danh sách sản phẩm");
        model.addAttribute("filterCategoryProduct", _iProductService.getSetCategoryDetailsByIdCategory(null));
        model.addAttribute("filterColorProduct", _iProductService.getSetColorByIdCategory(null));
        model.addAttribute("filterPriceProduct", _iProductService.getMinMaxPrice());
        return "page/web/product/list-product";
    }

}
