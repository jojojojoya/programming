package com.sj.spring06.controller;

import com.sj.spring06.service.ProductService;
import com.sj.spring06.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("products")
@Controller
public class ProductC2 {
    //rest API 더 예쁘게
    // GET / products : 모든 제품조회
    // GET / products/{id} : 특정 제품조회
    // POST / products : 새 제품 생성
    // PUT / products/{id} : 특정 제품 업데이트
    // DELETE / products/{id} : 특정 제품 삭제
    @Autowired
    private ProductService productService;

    @GetMapping
    public String products(Model model) {
        System.out.println("get 요청 들어옴 --");
        model.addAttribute("products",productService.getProducts());
        return "product/products2";

    }

    @PostMapping
    public String products(ProductVO productVO) {
        System.out.println("post 요청 들어옴 --");

        productService.addProduct(productVO);
        return "redirect:/products";

    }


    @DeleteMapping("/{pk}")

    public String deleteproducts(int pk) { // 여기 받은 파라미터 이름을 적어야 함
        System.out.println("delete 요청 들어옴 --");
        productService.deleteProduct(pk);
        return "redirect:/products";

    }

    @PutMapping
    public String updateproducts(ProductVO productVO) { //객체로 받은 이유는, 다른값이 있기 때문임
        System.out.println("update 요청 들어옴 --");

        productService.updateProduct(productVO.getP_no());
        return "redirect:/products";

    }
}
