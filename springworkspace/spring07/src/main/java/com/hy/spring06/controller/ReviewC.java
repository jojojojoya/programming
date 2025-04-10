package com.hy.spring06.controller;

import com.hy.spring06.service.ReviewService;
import com.hy.spring06.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/review")
@Controller
public class ReviewC {

    // url 구조
    // /review/all
    // /review/detail
    // /review/create
    // /review/delete
    // /review/update
    // /review/api/update


    @Autowired
    private ReviewService reviewService;

    @GetMapping("all")
    public String review(Model model) {

        model.addAttribute("reviews", reviewService.getReviews());
        return "review/reviews";

    }

    @GetMapping("/create")
    public String reviewAdd(Model model) {
        return "review/review_add";
    }

    @PostMapping("/create")
    public String reviewReg(ReviewVO reviewVO) {

        reviewService.addReview(reviewVO);
        return "redirect:all";

    }

    @GetMapping("/detail")
    public String reviewDetail(int no, Model model) {
        model.addAttribute("review", reviewService.getReview(no));
        return "review/review_detail";

    }

    @GetMapping("/detail2")
    public String reviewDetail2(int no, Model model) {
        model.addAttribute("review", reviewService.getReview(no));
        return "review/review_detail2";

    }

    @GetMapping("/delete")
    public String reviewDelete(int no) {
        reviewService.delReview(no);
        return "redirect:all";
    }

    @GetMapping("/update")
    public String updateReview(int no, Model model) {
        // 기존값
        model.addAttribute("review", reviewService.getReview(no));
        return "review/review_update";
    }

    @PostMapping("/update")
    public String updateReview(ReviewVO reviewVO) {
        reviewService.updateReview(reviewVO);
        // 디테일 페이지로 가려면 업데이트 된 내용을 가져가야 함
        return "redirect:/review-detail?no=" + reviewVO.getR_no();
    }

    // 비동기. AJAX
    @ResponseBody
    @PostMapping("/api/update")
    public int updateReviewAJAX(ReviewVO reviewVO) {
        return reviewService.updateReview(reviewVO);
    }





}
