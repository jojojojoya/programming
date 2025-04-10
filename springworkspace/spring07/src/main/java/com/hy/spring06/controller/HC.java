package com.hy.spring06.controller;

import com.hy.spring06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HC {

    @Autowired
    private ProductService m;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/api/product")
    public String apiProduct() {
        return "product/products_async";

    }
    @GetMapping("/upload")
    public String file() {
        return "file";
    }








}
