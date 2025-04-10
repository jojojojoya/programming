package com.hy.spring06.service;

import com.hy.spring06.mapper.ReviewMapper;
import com.hy.spring06.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<ReviewVO> getReviews() {

        return reviewMapper.getReviews();

    }

    public void addReview(ReviewVO reviewVO) {

        reviewMapper.addReview(reviewVO);

    }

    public ReviewVO getReview(int no) {

        return reviewMapper.getReview(no);

    }

    public void delReview(int no) {

        reviewMapper.delReview(no);

    }

    public int updateReview(ReviewVO reviewVO) {

        return reviewMapper.updateReview(reviewVO);

    }
}
