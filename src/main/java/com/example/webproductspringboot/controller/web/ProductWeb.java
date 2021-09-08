package com.example.webproductspringboot.controller.web;

import com.example.webproductspringboot.dto.category.SearchCategoryDto;
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
        if (idCategory != null && idCategory.isEmpty() && idCategory.isBlank()
        && pathUrl != null && pathUrl.isEmpty() && pathUrl.isBlank()) {
            try {
                SearchCategoryDto searchCategoryDto = _iCategoryService.findByIdAndPath(idCategory, pathUrl);
                if (searchCategoryDto != null) {
                    model.addAttribute("listProduct", _iProductService.findByIdCategory(searchCategoryDto.getIdCategory()));
                    model.addAttribute("title", searchCategoryDto.getName());
                    return "page/web/product/list-product";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return showList(model);
            }
        }
        return showList(model);
    }

    @GetMapping("/product")
    public String showList(Model model) {
        model.addAttribute("listProduct", _iProductService.findAll());
        model.addAttribute("title", "Danh sách sản phẩm");
        return "page/web/product/list-product";
    }

}
