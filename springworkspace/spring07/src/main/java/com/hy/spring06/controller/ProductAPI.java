package com.hy.spring06.controller;

import com.hy.spring06.service.ProductService;
import com.hy.spring06.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/product")
@RestController
// controller + responsebody >> 이 공간안에 작성하는 모든 내용은 자동적으로 제이슨을 응답하게 됨
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<ProductVO> products() {
        return productService.getProducts();

    }

    @GetMapping("/{no}")
    public void getProducts(@PathVariable int no) {
        System.out.println(no);
    }

    @DeleteMapping("/{no}")
    public int deleteProduct(@PathVariable int no) {
        System.out.println(no);
        return productService.delProduct(no);
    }

    @PutMapping()
    public int updateProduct(@RequestBody ProductVO productVO) {
        System.out.println(productVO);
        return productService.updateProduct(productVO);
    }}


