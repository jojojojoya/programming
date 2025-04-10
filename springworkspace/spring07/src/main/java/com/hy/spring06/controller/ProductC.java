package com.hy.spring06.controller;

import com.hy.spring06.service.ProductService;
import com.hy.spring06.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
@Controller
public class ProductC {

    // url - 조금 이쁘게 만들자
    // /product/all
    // /product/detail
    // /product/create
    // /product/update
    // /product/delete

    @Autowired
    private ProductService productService;

    // 전체 조회
    @GetMapping("/all")
    public String products(Model model) {

        model.addAttribute("products", productService.getProducts());
        return "product/products";
    }

    @PostMapping("/create")
    public String addProduct(ProductVO productVO) {

        productService.addProduct(productVO);
        return "redirect:all";

    }

    @GetMapping("/delete")
    public String delProduct(int pk) {

        productService.delProduct(pk);
        return "redirect:all";

    }

    @PostMapping("/update")
    public String modiProduct(ProductVO productVO) {

        productService.modiProduct(productVO);
        return "redirect:all";
    }

}
