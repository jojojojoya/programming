package com.jojonezip.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jojonezip.demo.service.IndexProductService;
import com.jojonezip.demo.vo.ProductVO;

@Controller
public class CategoriesController {
	
	@Autowired
	private IndexProductService indexProductService;
	
	@GetMapping("/outers")
	public String outerlisting(Model model) {
		List<ProductVO> productList = indexProductService.productListingByCategory("outer");
		model.addAttribute("productList",productList);
		
		return "categories/outers";
	}

	@GetMapping("/accs")
	public String acclisting(Model model) {
		List<ProductVO> productList = indexProductService.productListingByCategory("acc");
		model.addAttribute("productList",productList);

		return "categories/accs";
	}
	
	@GetMapping("/bottoms")
	public String bottomlisting(Model model) {
		List<ProductVO> productList = indexProductService.productListingByCategory("bottom");
		model.addAttribute("productList",productList);
		
		if (productList.isEmpty()) {
			System.out.println("상품이 없어요~");
		}
		return "categories/bottoms";
	}
	
	
	@GetMapping("/tops")
	public String toplisting(Model model) {
		List<ProductVO> productList = indexProductService.productListingByCategory("top");
		model.addAttribute("productList",productList);
		
		return "categories/tops";
	}

	@GetMapping("/sales")
	public String salelisting(Model model) {
		List<ProductVO> productList = indexProductService.productListingByCategory("sale");
		model.addAttribute("productList",productList);
		
		return "categories/sales";
	}

	

}
