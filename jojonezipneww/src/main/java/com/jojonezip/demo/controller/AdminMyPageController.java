package com.jojonezip.demo.controller;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jojonezip.demo.mapper.AdminMyPageMapper;
import com.jojonezip.demo.mapper.CartMapper;
import com.jojonezip.demo.service.AdminMyPageService;
import com.jojonezip.demo.service.LoginService;
import com.jojonezip.demo.service.QnaService;
import com.jojonezip.demo.service.ReviewService;
import com.jojonezip.demo.service.UserMyPageService;
import com.jojonezip.demo.vo.OrderVO;
import com.jojonezip.demo.vo.ProductVO;
import com.jojonezip.demo.vo.QnaVO;
import com.jojonezip.demo.vo.ReviewVO;
import com.jojonezip.demo.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminMyPageController {

	private final AdminMyPageService adminMyPageService;

	@Autowired
	private LoginService loginService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private QnaService qnaService;
	@Autowired
	private FileSaveUtil fileSaveUtil;
	@Autowired
	private AdminMyPageMapper adminMyPageMapper;

	AdminMyPageController(AdminMyPageService adminMyPageService) {
		this.adminMyPageService = adminMyPageService;
	}

	@GetMapping("/adminmypage")
	public String adminmypage(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		int userCount = adminMyPageService.getUserCount();
		int reviewCount = adminMyPageService.getReviewCount();
		int qnaCount = adminMyPageService.getQnaCount();
		int uriageCount = adminMyPageService.getUriageCount();
		int orderCount = adminMyPageService.getOrdersCount();
		model.addAttribute("userCount", userCount);
		model.addAttribute("reviewCount", reviewCount);
		model.addAttribute("qnaCount", qnaCount);
		model.addAttribute("uriageCount", uriageCount);
		model.addAttribute("orderCount", orderCount);

		System.out.println("관리자입니다. ID: " + loginUser.getUser_id() + ", Type:" + loginUser.getUser_type());
		return "adminmypage/adminmypage";
	}

	@GetMapping("/adminuserlist")
	public String adminuserlist(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		List<UserVO> userList = adminMyPageService.showUserList();
		model.addAttribute("userList", userList);
		System.out.println("관리자입니다. ID: " + loginUser.getUser_id() + ", Type:" + loginUser.getUser_type());
		return "adminmypage/adminuserlist";
	}

	@GetMapping("/adminreview")
	public String adminreviewlist(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		List<ReviewVO> reviews = adminMyPageService.showReviewList();
		model.addAttribute("reviews", reviews);
		return "adminreviewdetail/adminreviewlist";
	}

	@GetMapping("/adminqna")
	public String adminqnalist(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		List<QnaVO> qnas = adminMyPageService.showQnaList();
		model.addAttribute("qnas", qnas);
		return "adminqnadetail/adminqnalist";
	}

	@GetMapping("/adminproduct")
	public String adminproductlist(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		List<ProductVO> product = adminMyPageService.showProductList();
		model.addAttribute("product", product);
		return "adminproduct/adminproduct";
	}

	@PostMapping("/delete/userlist")
	public String deleteUserList(HttpSession session, @RequestParam("userId") String userId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		adminMyPageService.deleteUser(userId);
		return "redirect:/adminuserlist";

	}

	@GetMapping("/addproductlist")
	public String showaddproductList(HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		return "adminproduct/adminproductadd";
	}

	@PostMapping("/add/product")
	public String addProductList(HttpSession session, @RequestParam("productCategory") String productCategory,
			@RequestParam("productName") String productName, @RequestParam("productPrice") int productPrice,
			@RequestParam("productAmount") int productAmount, @RequestParam("productIntrotext") String productIntrotext,
			@RequestParam("productInfocontent") String productInfocontent,
			@RequestParam("imageFile") MultipartFile imageFile) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		String savedFilePath = fileSaveUtil.save(imageFile);
		ProductVO product = new ProductVO();
		product.setProduct_category(productCategory);
		product.setProduct_name(productName);
		product.setProduct_price(productPrice);
		product.setProduct_amount(productAmount);
		product.setProduct_introtext(productIntrotext);
		product.setProduct_image(savedFilePath);

		adminMyPageService.addProduct(product);
		adminMyPageService.addProductInfo(product.getProduct_id(), productInfocontent);
		return "redirect:/adminproduct";
	}

	@GetMapping("/adduserlist")
	public String showadduserList(HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		return "adminmypage/adduserlist";
	}

	@PostMapping("/add/user")
	public String addUserList(HttpSession session, @RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword, @RequestParam("userNickname") String userNickname) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		adminMyPageService.addUser(userId, userPassword, userNickname);
		return "redirect:/adminuserlist";
	}

	@GetMapping("/adminreviewdetail")
	public String adminReviewDetail(HttpSession session, @RequestParam("reviewId") int reviewId, Model model) {
		ReviewVO reviewDetail = reviewService.getReviewById(reviewId);
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		model.addAttribute("reviewDetail", reviewDetail);
		return "adminreviewdetail/adminreviewdetail"; // 보여줄 JSP 경로
	}

	@GetMapping("/adminqnadetail")
	public String adminqnaDetail(HttpSession session, @RequestParam("qnaId") int qnaId, Model model) {
		QnaVO qnaDetail = qnaService.getQnaByQnaId(qnaId);
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		model.addAttribute("qnaDetail", qnaDetail);
		return "adminqnadetail/adminqnadetail";
	}

	@PostMapping("/insert/reviewanswer")
	public String insertReviewAnswer(HttpSession session, @RequestParam("review_answer") String reviewAnswer,
			@RequestParam("review_id") int reviewId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		adminMyPageService.insertReviewAnswer(reviewAnswer, reviewId);
		return "redirect:/adminreviewdetail?reviewId=" + reviewId;
	}

	@GetMapping("/delete/reviewanswer")
	public String deleteReviewAnswer(HttpSession session, @RequestParam("reviewId") int reviewId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		adminMyPageService.deleteReviewAnswer(reviewId);
		return "redirect:/adminreviewdetail?reviewId=" + reviewId;
	}

	@PostMapping("/insert/qnaanswer")
	public String insertQnaAnswer(HttpSession session, @RequestParam("qna_answer") String qnaAnswer,
			@RequestParam("qna_id") int qnaId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		adminMyPageService.insertQnaAnswer(qnaAnswer, qnaId);
		return "redirect:/adminqnadetail?qnaId=" + qnaId;
	}

	@GetMapping("/delete/qnaanswer")
	public String deleteQnaAnswer(HttpSession session, @RequestParam("qnaId") int qnaId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		adminMyPageService.deleteQnaAnswer(qnaId);
		return "redirect:/adminqnadetail?qnaId=" + qnaId;
	}

	@GetMapping("/adminproductdetail")
	public String showProductDetail(@RequestParam("productId") int productId, Model model, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		ProductVO product = adminMyPageService.getProductById(productId);
		model.addAttribute("product", product);
		return "adminproduct/adminproductdetail";
	}

	@PostMapping("/update/product")
	public String updateProduct(@ModelAttribute ProductVO product,
			@RequestParam("product_infocontent") String productInfocontent,
			@RequestParam("imageFile") MultipartFile file, @RequestParam("originalImage") String originalImage) {

		if (!file.isEmpty()) {
			String savedFileName = fileSaveUtil.save(file);
			product.setProduct_image(savedFileName);
		} else {
			product.setProduct_image(originalImage);

		}
		adminMyPageService.updateProduct(product);
		adminMyPageService.updateProductInfo(product.getProduct_id(), productInfocontent);
		System.out.println("productId: " + product.getProduct_id());
		System.out.println("content: " + productInfocontent);

		return "redirect:/adminproduct";
	}

	@PostMapping("/delete/product")
	public String deleteProduct(@RequestParam("productId") int productId, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		adminMyPageService.deleteProductById(productId);
		return "redirect:/adminproduct";
	}

	@GetMapping("/adminorder")
	public String showAdminOrderList(HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}

		// 전체 주문 리스트 (상품 여러 개가 하나의 주문에 포함될 수 있음)
		List<Map<String, Object>> fullOrderList = adminMyPageMapper.getOrder();

		// ✅ 주문번호 기준으로 대표 상품 1개만 추출
		Map<Integer, Map<String, Object>> uniqueOrderMap = new LinkedHashMap<>();
		for (Map<String, Object> order : fullOrderList) {
			Integer orderId = (Integer) order.get("order_id");
			if (!uniqueOrderMap.containsKey(orderId)) {
				uniqueOrderMap.put(orderId, order); // 대표 상품 1개만 저장
			}
		}

		List<Map<String, Object>> uniqueOrderList = new ArrayList<>(uniqueOrderMap.values());

		model.addAttribute("orderList", uniqueOrderList);
		return "adminorderdetail/adminorderlist";
	}
	
	@GetMapping("/adminorderdetail") 
	public String showAdminOrderDetail(@RequestParam("orderId") int orderId, HttpSession session, Model model) 
	{ UserVO loginUser = (UserVO) session.getAttribute("loginUser");
	if (loginUser == null || loginUser.getUser_type() != 2) {
		return "redirect:/loginpage";
	}

	OrderVO orderDetail = adminMyPageMapper.getOrderSummaryByOrderIdSingle(orderId);
	List<Map<String, Object>> productList = adminMyPageService.getOrderDetailByOrderId(orderId);
	
	int totalPrice = 0;
	for(Map<String, Object> item : productList) {
		int amount = (int)item.get("product_amount");
		int price = (int)item.get("product_price");
		totalPrice += amount * price;
	}
	
	model.addAttribute("orderDetail",orderDetail);
	model.addAttribute("productList",productList);
	model.addAttribute("totalPrice",totalPrice);
	
	return "adminorderdetail/adminorderdetail";
	}
	
	

	@GetMapping("/cancelcomplete")
	public String cancelCompleteOrder(HttpSession session, @RequestParam("orderId") int orderId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		adminMyPageMapper.updateOrderStatusToCancel(orderId,"주문 취소완료");
		return "redirect:/adminorder";  // ✅ 여기!
	}

	@GetMapping("/deliveredorder")
	public String updateOrderStatusToDelivered(HttpSession session, @RequestParam("orderId") int orderId) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getUser_type() != 2) {
			return "redirect:/loginpage";
		}
		adminMyPageMapper.updateOrderStatusToDelivered(orderId, "배송 처리완료");
		return "redirect:/adminorder";  // ✅ 여기!
	}


}
