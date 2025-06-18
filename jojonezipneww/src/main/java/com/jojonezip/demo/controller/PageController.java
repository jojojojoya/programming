package com.jojonezip.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.service.IndexProductService;
import com.jojonezip.demo.vo.ProductVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	@Autowired
	private IndexProductService indexProductService;
	
	@GetMapping("/")
	public String home(Model model, HttpSession session, @RequestParam(name = "logout", required = false) String logout) {
		if ("true".equals(logout)) {
			session.invalidate();
			System.out.println(">>> /로그아웃 후 홈이동");
			return "redirect:/";
		}
		
			
		System.out.println(">>> / 메인페이지 접속");
		List<ProductVO> productList = indexProductService.productListing();
		model.addAttribute("productList", productList);
		
	    return "main/index";
	}


	@GetMapping("/loginpage")
	public String loginPage() {
		return "login/loginpage";
	}
	
	@GetMapping("/backtouserorder")
	public String backtouserorder() {
		return "userorderdetail/userorder";
	}


	@GetMapping("/joinpage")
	public String joinpage() {
		return "join/joinpage";
	}

}

