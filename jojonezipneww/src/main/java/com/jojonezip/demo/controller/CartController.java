package com.jojonezip.demo.controller;

import java.util.Iterator;
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
import com.jojonezip.demo.service.IndexProductService;
import com.jojonezip.demo.service.JoinService;
import com.jojonezip.demo.service.ReviewService;
import com.jojonezip.demo.vo.CartVO;
import com.jojonezip.demo.vo.OrderVO;
import com.jojonezip.demo.vo.OrderitemVO;
import com.jojonezip.demo.vo.ProductVO;
import com.jojonezip.demo.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private final ReviewService reviewService;

	private final JoinController joinController;

	@Autowired
	private CartService cartService;
	@Autowired
	private IndexProductService indexProductService;

	CartController(JoinController joinController, ReviewService reviewService) {
		this.joinController = joinController;
		this.reviewService = reviewService;
	}

	@PostMapping("/cart/add")
	public String addToCart(@RequestParam("productId") int productId,
			@RequestParam(value = "quantity", defaultValue = "1") int quantity, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null)
			return "redirect:/loginpage";
		cartService.addToCart(loginUser.getUser_id(), productId, quantity);
		return "redirect:/cartpage";
	}

	@PostMapping("/cart/delete")
	public String deleteCartItem(@RequestParam("productId") int productId, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser != null) {
			cartService.deleteCartItem(loginUser.getUser_id(), productId);
		}
		return "redirect:/cartpage"; // 삭제 후 장바구니 새로고침
	}

	@PostMapping("/cart/updateQuantity")
	public String updateCartQuantity(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity,
			HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser != null) {
			cartService.updateCartQuantity(loginUser.getUser_id(), productId, quantity);
		}
		return "redirect:/cartpage";
	}

	@GetMapping("/cartpage")
	public String viewcartpage(HttpSession session, Model model) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";

		}

		List<CartVO> cartList = cartService.getCartListByUserId(loginUser.getUser_id());
		model.addAttribute("cartList", cartList);

		if (cartList == null || cartList.isEmpty()) {
			model.addAttribute("emptyCart", "장바구니에 담긴 상품이 없습니다.");

		}
		return "cart/cartpage";
	}

	@PostMapping("/orderpage")
	public String gotoOrderPage(@RequestParam("productIds") List<Integer> productIds,
			@RequestParam("quantities") List<Integer> quantities, @RequestParam("prices") List<Integer> prices,
			@RequestParam("productNames") List<String> productNames,
			@RequestParam("productImages") List<String> productImages, Model model, HttpSession session) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";

		}
		int totalPrice = 0;
		for (int i = 0; i < productIds.size(); i++) {
			totalPrice += quantities.get(i) * prices.get(i);

		}

		model.addAttribute("productIds", productIds);
		model.addAttribute("quantities", quantities);
		model.addAttribute("prices", prices);
		model.addAttribute("productNames", productNames);
		model.addAttribute("productImages", productImages);
		model.addAttribute("totalPrice", totalPrice);
		return "cart/orderpage"; // JSP 페이지

	}

	@Autowired
	private CartMapper cartMapper;

	@PostMapping("/completeorder")
	public String completeOrder(@RequestParam("productIds") List<Integer> productIds,
			@RequestParam("quantities") List<Integer> quantities, @RequestParam("prices") List<Integer> prices,
			@RequestParam("productNames") List<String> productNames,
			@RequestParam("productImages") List<String> productImages, @RequestParam("ordername") String ordername,
			@RequestParam("ordertel") String ordertel, @RequestParam("orderaddress") String orderaddress,
			HttpSession session, Model model) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null)
			return "redirect:/loginpage";

		OrderVO order = new OrderVO();
		order.setUserId(loginUser.getUser_id());
		order.setOrderName(ordername);
		order.setOrderTel(ordertel);
		order.setOrderAddress(orderaddress);

		cartMapper.insertOrder(order); // order_id 자동 생성

		for (int i = 0; i < productIds.size(); i++) {
			int stock = cartService.getProductStock(productIds.get(i));
			if (quantities.get(i) > stock) {
				model.addAttribute("errorMsg", "상품 재고 부족으로 주문 불가합니다. 현재 남은 상품은 " + stock + "개입니다.");

				model.addAttribute("productIds", productIds);
				model.addAttribute("quantities", quantities);
				model.addAttribute("prices", prices);
				model.addAttribute("productNames", productNames);
				model.addAttribute("productImages", productImages);
				model.addAttribute("ordername", ordername);
				model.addAttribute("ordertel", ordertel);
				model.addAttribute("orderaddress", orderaddress);
				
				int totalPrice = 0;
				for (int j = 0; j < prices.size(); j++) {
					totalPrice += prices.get(j) * quantities.get(j);
					
				}
				model.addAttribute("totalPrice", totalPrice);
				return "cart/orderpage";

			}
			OrderitemVO item = new OrderitemVO();
			item.setOrderId(order.getOrderId()); // ✅ 이거 중요!
			item.setProductId(productIds.get(i));
			item.setProductAmount(quantities.get(i));
			item.setProductPrice(prices.get(i));
			cartMapper.insertOrderItem(item);

			cartService.decreaseStock(productIds.get(i), quantities.get(i));
		}

		int totalPrice = 0;
		for (int i = 0; i < prices.size(); i++) {
			totalPrice += prices.get(i) * quantities.get(i);
		}

		// model 전달
		model.addAttribute("productIds", productIds);
		model.addAttribute("quantities", quantities);
		model.addAttribute("prices", prices);
		model.addAttribute("productNames", productNames);
		model.addAttribute("productImages", productImages);
		model.addAttribute("ordername", ordername);
		model.addAttribute("ordertel", ordertel);
		model.addAttribute("orderaddress", orderaddress);
		model.addAttribute("totalPrice", totalPrice);

		// 주문한 상품 장바구니에서 제거
		for (int i = 0; i < productIds.size(); i++) {
			cartService.deleteCartItem(loginUser.getUser_id(), productIds.get(i));
		}

		return "cart/ordercompletepage"; // 주문 완료 페이지로 이동
	}

	@GetMapping("/userorderdetail")
	public String showOrderDetail(@RequestParam("orderId") int orderId, Model model, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";
		}

		OrderVO orderDetail = cartMapper.getOrderSummaryByOrderIdSingle(orderId);
		List<Map<String, Object>> productList = cartService.getOrderDetailByOrderId(orderId);

		// 💰 총합 계산
		int totalPrice = 0;
		for (Map<String, Object> item : productList) {
			int amount = (int) item.get("product_amount");
			int price = (int) item.get("product_price");
			totalPrice += amount * price;
		}

		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("productList", productList);
		model.addAttribute("totalPrice", totalPrice); // ⬅️ 총합 전달

		return "userorderdetail/userorderdetail";
	}

	@PostMapping("/buydirect")
	public String buyDirect(@RequestParam("productId") int productId, @RequestParam("productPrice") int productPrice,
			@RequestParam(value = "quantity", defaultValue = "1") int quantity, HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null)
			return "redirect:/loginpage";

		ProductVO product = indexProductService.showProductDetail(productId);

		model.addAttribute("productIds", List.of(productId));
		model.addAttribute("productNames", List.of(product.getProduct_name())); // 여기서 가져오니까
		model.addAttribute("productImages", List.of(product.getProduct_image()));
		model.addAttribute("quantities", List.of(quantity));
		model.addAttribute("prices", List.of(productPrice));
		model.addAttribute("totalPrice", productPrice * quantity);

		return "cart/orderpage";
	}

	@GetMapping("/product/stock")
	@ResponseBody
	public int getProductStock(@RequestParam("productId") int productId) {
		return cartService.getProductStock(productId);

	}

}
