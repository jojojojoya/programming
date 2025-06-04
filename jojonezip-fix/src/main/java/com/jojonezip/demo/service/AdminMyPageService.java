package com.jojonezip.demo.service;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jojonezip.demo.controller.AdminMyPageController;
import com.jojonezip.demo.mapper.AdminMyPageMapper;
import com.jojonezip.demo.vo.ProductVO;
import com.jojonezip.demo.vo.QnaVO;
import com.jojonezip.demo.vo.ReviewVO;
import com.jojonezip.demo.vo.UserVO;

@Service
public class AdminMyPageService {


	@Autowired
	private AdminMyPageMapper adminMyPageMapper;


	public List<UserVO> showUserList() {
		return adminMyPageMapper.showUserList();
	}
	
	public List<QnaVO> showQnaList() {
		return adminMyPageMapper.showQnaList();
	}
	
	public List<ReviewVO> showReviewList() {
		return adminMyPageMapper.showReviewList();
	}
	
	public List<ProductVO> showProductList() {
		return adminMyPageMapper.showProductList();
		
	}
	
	public int getUserCount() {
	    return adminMyPageMapper.getUserCount();
	}
	
	public int getReviewCount() {
		return adminMyPageMapper.getReviewCount();
	}
	
	public int getQnaCount() {
		return adminMyPageMapper.getQnaCount();
	}
	
	public int getOrdersCount(){
		return adminMyPageMapper.getOrdersCount();
	}
	
	public int getUriageCount() {
		return adminMyPageMapper. getUriageCount();
	}
	
	public int deleteUser(String userId) {
		return adminMyPageMapper.deleteUser(userId);
	}
	

	public int addUser(String userId, String userPassword, String userNickname) {
	    return adminMyPageMapper.addUser(userId, userPassword, userNickname);
	}
	    public int insertReviewAnswer(String reviewAnswer, int reviewId) {
	        return adminMyPageMapper.insertReviewAnswer(reviewAnswer, reviewId);


}
	    public int addProduct(ProductVO product) {
	        return adminMyPageMapper.addProduct(product);
	    }

	    public int addProductInfo(int productId, String infoContent) {
	        return adminMyPageMapper.addProductInfo(productId, infoContent);
	    }

	    
	    public int deleteReviewAnswer(int reviewId) {
	    	return adminMyPageMapper.deleteReviewAnswer(reviewId);
	    }
	    
	    public int insertQnaAnswer(String qnaAnswer, int qnaId) {
	        return adminMyPageMapper.insertQnaAnswer(qnaAnswer, qnaId);


}
	    
	    public ProductVO getProductById(int productId) {
	    	return adminMyPageMapper.getProductById(productId);
	    }
	    
	    public void updateProductInfo(int productId, String infoContent) {
	    	adminMyPageMapper.updateProductInfo(productId, infoContent);
	    }
	    
	    public int updateProduct(ProductVO product) {
	    	return adminMyPageMapper.updateProduct(product);
	    }
	    
	    public int deleteProductById(int productId) {
	    	return adminMyPageMapper.deleteProductById(productId);
	    }
	    
	    public int deleteQnaAnswer(int qnaId) {
	    	return adminMyPageMapper.deleteQnaAnswer(qnaId);
	    }
	    
	    List<Map<String, Object>> getOrder() {
	    	return adminMyPageMapper.getOrder();
	    }
	    
	    public List<Map<String, Object>>  getOrderDetailByOrderId(int orderId) {
	    	return adminMyPageMapper.getOrderDetailByOrderId(orderId);
}
}
