package com.jojonezip.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.mapper.ReviewMapper;
import com.jojonezip.demo.vo.ReviewVO;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;
	
	
	public List<ReviewVO> getReviewsByProductId(int productId) {
		return reviewMapper.getReviewsByProductId(productId);
	}

	public List<ReviewVO> getReviewsByUserId(String userId) {
		return reviewMapper.getReviewsByUserId(userId);
	}
	
	public ReviewVO getReviewByReviewId(int reviewId){
	    return reviewMapper.getReviewByReviewId(reviewId);
	}
	
	public void updateReview(ReviewVO review) {
	    reviewMapper.updateReview(review);
	}
	public void deleteReviewById(int reviewId) {
	    reviewMapper.deleteReviewById(reviewId); // 굳이 반환값 안 써도 됨
	}
	
	public void insertReviewById(ReviewVO review) {
	reviewMapper.insertReviewById(review);
	}
	
	
	public ReviewVO getReviewById(int reviewId) {
	    return reviewMapper.getReviewById(reviewId);  // ✅ SELECT * FROM review ❌ 아님
	}
	
	public boolean hasWrittenReview(String userId, int productId) {
	    return reviewMapper.hasWrittenReview(userId, productId) > 0;
	}

}



