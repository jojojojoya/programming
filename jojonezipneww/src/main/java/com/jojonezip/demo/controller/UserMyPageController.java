package com.jojonezip.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojonezip.demo.mapper.CartMapper;
import com.jojonezip.demo.service.CartService;
import com.jojonezip.demo.service.QnaService;
import com.jojonezip.demo.service.ReviewService;
import com.jojonezip.demo.service.UserMyPageService;
import com.jojonezip.demo.vo.OrderVO;
import com.jojonezip.demo.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserMyPageController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserMyPageService userMyPageService;

	@Autowired
	private QnaService qnaService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartMapper cartMapper;
	
	UserMyPageController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/usermypage")
	public String usermypage(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";
		}

		List<Map<String, Object>> fullOrderList = cartService.getOrderSummaryByUserId(loginUser.getUser_id());
		
		
		Map<String, Object> summary = cartMapper.getTotalCountAndPriceByUserId(loginUser.getUser_id());
		int totalCount = summary.get("total_count") != null ? ((Number) summary.get("total_count")).intValue() : 0;
		int totalPrice = summary.get("total_price") != null ? ((Number) summary.get("total_price")).intValue() : 0;

		
		
		Map<Integer, Map<String, Object>> uniqueOrderMap = new LinkedHashMap<>();
		for (Map<String, Object> order : fullOrderList) {
			Integer orderId = (Integer) order.get("order_id");
			if (!uniqueOrderMap.containsKey(orderId)) {
				uniqueOrderMap.put(orderId, order);
			}
		}
		List<Map<String, Object>> uniqueOrderList = new ArrayList<>(uniqueOrderMap.values());

		model.addAttribute("orderList", uniqueOrderList);
		model.addAttribute("reviewList", reviewService.getReviewsByUserId(loginUser.getUser_id()));
		model.addAttribute("qnaList", qnaService.getQnaByUserId(loginUser.getUser_id()));
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("totalPrice", totalPrice);
	    
		return "usermypage/usermypage";
	}


	@GetMapping("/userqna")
	public String userqnadetail(HttpSession session, Model model) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";

		}
		model.addAttribute("qnaList", qnaService.getQnaByUserId(loginUser.getUser_id()));
		return "userqnadetail/userqna";

	}

	@GetMapping("/userreview")
	public String userreivewdetail(HttpSession session, Model model) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";

		}
		model.addAttribute("reviewList", reviewService.getReviewsByUserId(loginUser.getUser_id()));
		return "userreviewdetail/userreview";

	}

	@PostMapping("/updateNickname")
	@ResponseBody
	public String updateNickname(@RequestParam("nickname") String nickname, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null)
			return "로그인이 필요합니다.";

		loginUser.setUser_nickname(nickname);
		userMyPageService.updateNickname(loginUser.getUser_id(), nickname);
		return "닉네임이 변경되었습니다.";

	}

	@GetMapping("/userorder")
	public String userorder(HttpSession session, Model model) {
		// 여기서 로그인유저 세션에 있는 id,닉네임값가져와서 마이페이지 영역에 뿌리기

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";

		}

		List<Map<String, Object>> fullOrderList = cartService.getOrderSummaryByUserId(loginUser.getUser_id());

		Map<Integer, Map<String, Object>> uniqueOrderMap = new LinkedHashMap<>();
		for (Map<String, Object> order : fullOrderList) {
			Integer orderId = (Integer) order.get("order_id");
			if (!uniqueOrderMap.containsKey(orderId)) {
				uniqueOrderMap.put(orderId, order); // 대표 상품 1개만 넣기
			}
		}

		List<Map<String, Object>> uniqueOrderList = new ArrayList<>(uniqueOrderMap.values());

		model.addAttribute("orderList", uniqueOrderList);

		return "userorderdetail/userorder";
	}

	@GetMapping("/cancelorder")
	public String cancelorder(HttpSession session, @RequestParam("orderId") int orderId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";
		}

		OrderVO orderDetail = cartMapper.getOrderSummaryByOrderIdSingle(orderId);
		orderDetail.setOrderStatus("주문 취소요청");

		// 실제 DB 반영을 위해 업데이트 쿼리 필요함!
		cartMapper.updateOrderStatus(orderId, "주문 취소요청");
		return "redirect:/userorder";
	}
}
