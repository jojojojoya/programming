package com.jojonezip.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.mapper.CartMapper;
import com.jojonezip.demo.service.IndexProductService;
import com.jojonezip.demo.service.ProductInfoService;
import com.jojonezip.demo.service.QnaService;
import com.jojonezip.demo.service.ReviewService;
import com.jojonezip.demo.vo.ProductVO;
import com.jojonezip.demo.vo.UserVO;
import jakarta.servlet.http.HttpSession;


@Controller
public class ProductDetailController {

    private final ProductInfoService productInfoService;

	@Autowired
	private IndexProductService indexProductService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private QnaService qnaService;
 
 

    @Autowired
    private CartMapper cartMapper; // 또는 AdminMyPageMapper 등

    ProductDetailController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }
	

    
    
    @GetMapping("/productdetail")
    public String showProductDetail(Model model, @RequestParam("productId") int productId,
                                    @RequestParam(value = "tab", defaultValue = "info") String tab,
                                    HttpSession session) {

    	ProductVO productdetail = indexProductService.showProductDetail(productId);
    	model.addAttribute("productdetail", productdetail);
    	model.addAttribute("tab", tab);
    
    	if (tab.equals("info")) {
    		model.addAttribute("info", productInfoService.getInfoByProductId(productId));
    	}		else if (tab.equals("review")) {
    		    model.addAttribute("reviewList", reviewService.getReviewsByProductId(productId));

    		    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
    		    boolean canWriteReview = false;
    		    if (loginUser != null) {
    		        int purchased = cartMapper.hasPurchasedProduct(loginUser.getUser_id(), productId);
    		        boolean hasWritten = reviewService.hasWrittenReview(loginUser.getUser_id(), productId);
    		        canWriteReview = (purchased > 0) && !hasWritten;
    		    }
    		    model.addAttribute("canWriteReview", canWriteReview);
    		
    	} else if (tab.equals("qna")) {
    		model.addAttribute("qnaList", qnaService.getQnaByProductId(productId));
    	}

    	return "productdetail/productdetail";
    }

	
	
}
