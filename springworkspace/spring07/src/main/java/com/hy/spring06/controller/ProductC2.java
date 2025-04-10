package com.hy.spring06.controller;

import com.hy.spring06.service.ProductService;
import com.hy.spring06.vo.ProductVO;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@Controller
public class ProductC2 {

    // rest API 더 이쁘게
    // GET      /products       : 모든 제품 조회
    // GET      /products/{id}  : 특정 제품 조회
    // POST     /products       : 새 제품 생성
    // PUT      /products/{id}  : 특정 제품 업데이트
    // DELETE   /products/{id}  : 특정 제품 삭제


    @Autowired
    private ProductService productService;

    // 전체 조회
    @GetMapping
    public String products(Model model) {

        model.addAttribute("products", productService.getProducts());
        return "product/products2";
    }

    @PostMapping
    public String addProduct(ProductVO productVO) {

        productService.addProduct(productVO);
        return "redirect:products";

    }

    @GetMapping("/{pk}")
    public String delProduct(int pk) {
        System.out.println("delete 요청 들어옴");
        productService.delProduct(pk);
        return "redirect:products";

    }

    @PutMapping
    public String modiProduct(ProductVO productVO) {
        System.out.println("put 요청 들어옴");
        productService.modiProduct(productVO);
        return "redirect:products";
    }


    @ResponseBody
    @GetMapping("/getJSON/{no}")
    public ProductVO getJSON(@PathVariable  int no) {
         System.out.println(no);
         return productService.getProduct(no);

     }

     @ResponseBody
    @PostMapping("/api/product")
    public int apiProduct(@RequestBody ProductVO productVO) {
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println(productVO);
        System.out.println("~~~~~~~~~~~~~~~~~~");

        return productService.addProduct(productVO);
    }

    @GetMapping("/api/product/{no}")
    public String getProduct(@PathVariable  int no, Model model) {
        model.addAttribute("product", productService.getProduct(no));
        System.out.println(no);
        return "product/product_detail";

    }
}