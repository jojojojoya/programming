package com.sj.spring06.controller;

import com.sj.spring06.service.ProductService;
import com.sj.spring06.service.ReviewService;
import com.sj.spring06.vo.ProductVO;
import com.sj.spring06.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewC {

    @Autowired
    // 의존관계 주입
    // 객체를 새로 생성해서 활용할 시, 누구나 불러쓸 수있기에
    //
    private ReviewService reviewService;



    @GetMapping("reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews",reviewService.getReviews());
        return "reviews/reviews";

    }

    @GetMapping("reviews-add")
    public String addReviews() {
        return "reviews/reviewsadd";

    }


    @PostMapping("addreview")
    public String addReviews2(ReviewVO reviewVO) {
        reviewService.addReview(reviewVO);
        return "redirect:/reviews";

    }



//
//    @GetMapping("review-add")
//    public String reviewAdd(Model model) {
//        return "review/addReview";
//



//    }

    @GetMapping("reviews-detail")
    public String viewReviews(int no, Model model) {
       model.addAttribute("review", reviewService.viewReview(no));
       // attributename을 review라고 정의, 이 네임을 jsp에서 그려준다
        //reviewService.viewReview(no)에서 담긴 값을 review에서 쓴다.
       return "reviews/reviewsdetail";
        // requestparam => 외부에서 파라미터를 받는다
        // return은 갈 곳. 갈 페이지의 경로. 기본적으로는 html을 매핑하기 때문에, jsp의 경로를 잘 적어줘야함
    }



    @GetMapping("reviews-update")
    public String updateReviews(int no, Model model) {
        model.addAttribute("review", reviewService.viewReviewDetail(no));
        return "reviews/reviewsdeepdetail";

    }
    @PostMapping("reviews-update-complete")
    public String updateReviewsComplete(ReviewVO reviewVO, Model model) {
        // 리뷰 업데이트
        reviewService.updateReviewComplete(reviewVO);

        // 업데이트된 리뷰를 다시 불러와서 보여주기
        model.addAttribute("review", reviewService.viewReview(reviewVO.getR_no()));
        return "redirect:/reviews-detail?no=" + reviewVO.getR_no();
    }
}

//
//    @GetMapping("reviews-update-complete")
//    public String deleteReviews(int no) {
//        reviewService.deleteReview(no);
//        return "redirect:/reviewsdetail";

        //responsebody ->  내가 직접 지정한 데이터를 넘겨준다
        //return에 객체가 오면, 그냥 스프링에서 http로 데이터형식으로 반환하겠다






