package com.sj.spring06.service;


import com.sj.spring06.mapper.ReviewMapper;
import com.sj.spring06.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<ReviewVO> getReviews() {
        return reviewMapper.getReviews();

    }

    public void addReview(ReviewVO reviewVO) {
        // 수행했을 때 받은 값이 1 = 1행
        reviewMapper.addReview(reviewVO);
    }


    public ReviewVO viewReview(int no) {
        // 수행했을 때 받은 값이 1 = 1행
        return reviewMapper.viewReview(no);
    }


    public ReviewVO viewReviewDetail(int no) {
        // 수행했을 때 받은 값이 1 = 1행
        return reviewMapper.viewReviewDetail(no);
    }

    public void updateReviewComplete(ReviewVO reviewVO) {
        reviewMapper.updateReviewComplete(reviewVO);
    }


    public void deleteReview(int no) {
        reviewMapper.deleteReview(no);
    }
}

