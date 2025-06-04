package com.jojonezip.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.CartMapper;
import com.jojonezip.demo.mapper.LoginPageMapper;
import com.jojonezip.demo.vo.CartVO;
import com.jojonezip.demo.vo.OrderVO;
import com.jojonezip.demo.vo.UserVO;

@Service
public class CartService {
	
	@Autowired
	private CartMapper cartMapper;

	
	public List<CartVO> getCartListByUserId(String userId){
		return cartMapper.getCartListByUserId(userId);
	}
	
	public List<Map<String, Object>> getOrderSummaryByUserId(String userId) {
	    return cartMapper.getOrderSummaryByUserId(userId);
	}

	public void addToCart(String userId, int productId, int quantity) {
		 CartVO existing = cartMapper.findCartItem(userId, productId);
		    if (existing != null) {
		        // 이미 있으면 수량 증가
		        cartMapper.updateQuantity(userId, productId, existing.getQuantity() + quantity);
		    } else {
		        // 없으면 새로 추가
		        cartMapper.insertCartItem(userId, productId, quantity);
		    }
		}
	
	public void buyDirect(String userId, int productId, int quantity, int productPrice) {
		 CartVO existing = cartMapper.findCartItem(userId, productId);
		    if (existing != null) {
		        // 이미 있으면 수량 증가
		        cartMapper.updateQuantity(userId, productId, existing.getQuantity() + quantity);
		    } else {
		        // 없으면 새로 추가
		        cartMapper.insertCartItem(userId, productId, quantity);
		    }
		}
	
	
	public void deleteCartItem(String userId, int productId) {
	    System.out.println("장바구니 삭제: userId=" + userId + ", productId=" + productId);
	    cartMapper.deleteCartItem(userId, productId);
	}
	
	public void updateCartQuantity(String userId, int productId, int quantity) {
		cartMapper.updateQuantity(userId, productId, quantity);
	}
		public List<Map<String, Object>> getOrderDetailByOrderId(int orderId) {
		    return cartMapper.getOrderDetailByOrderId(orderId);

	}
		public void decreaseStock(int productId, int amount) {
			cartMapper.decreaseStock(productId, amount);
		}
		
		public int getProductStock(int productId) {
			return cartMapper.getProductStock(productId);
			
		}
}
