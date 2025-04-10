package com.sj.spring06.controller;

import com.sj.spring06.service.ProductService;
import com.sj.spring06.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductC {

    @Autowired
    private ProductService productService;

    @GetMapping("product")
    public String products(Model model) {
        model.addAttribute("products",productService.getProducts());
        return "product/products";

    }

    @PostMapping("product")
    public String products(ProductVO productVO) {
        productService.addProduct(productVO);
        return "redirect:/product";

    }


    @PostMapping("delete-product")
    public String deleteproducts(ProductVO productVO) {
        productService.deleteProduct(productVO.getP_no());
        return "redirect:/product";

    }

    @PostMapping("update-product")
    public String updateproducts(ProductVO productVO) {
        productService.updateProduct(productVO.getP_no());
        return "redirect:/product";

    }
}
