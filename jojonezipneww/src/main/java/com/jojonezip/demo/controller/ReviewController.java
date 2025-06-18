package com.jojonezip.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jojonezip.demo.service.ReviewService;
import com.jojonezip.demo.service.UserMyPageService;
import com.jojonezip.demo.vo.ReviewVO;
import com.jojonezip.demo.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {

	private final UserMyPageService userMyPageService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserMyPageService userPageService;

	ReviewController(UserMyPageService userMyPageService) {
		this.userMyPageService = userMyPageService;
	}

	@GetMapping("/userreviewdetail")
	public String showreviewdetails(@RequestParam("reviewId") int reviewId, HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/loginpage";
		}

		ReviewVO review = reviewService.getReviewByReviewId(reviewId);
		model.addAttribute("reviewDetail", review);
		model.addAttribute("now", System.currentTimeMillis());


		return "userreviewdetail/userreviewdetail";
	}

	@GetMapping("/review/updateForm")
	public String showReviewUpdateForm(@RequestParam("reviewId") int reviewId, Model model) {
		ReviewVO review = reviewService.getReviewByReviewId(reviewId);
		model.addAttribute("review", review);
		return "userreviewdetail/userreviewupdate";
	}

	@PostMapping("/review/update")
	public String updateReview(@ModelAttribute ReviewVO review, @RequestParam(value= "imageFile", required = false) MultipartFile file) {
		
		try {
		if (file != null && !file.isEmpty()) {
			
			  String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			  String uploadPath = "C:/jojonezip/upload/review"; // 운영체제 경로에 맞게
	            File dir = new File(uploadPath);
	            if (!dir.exists()) dir.mkdirs();

	            File saveFile = new File(uploadPath, fileName);
	            file.transferTo(saveFile);

	            
	            review.setReview_image(fileName);

	            System.out.println("리뷰 이미지 저장 경로: " + uploadPath + "/" + fileName);
	        } else {
	        	ReviewVO existing = reviewService.getReviewByReviewId(review.getReview_id());
	        	review.setReview_image(existing.getReview_image());
	        }}
		catch (IOException e) {
e.printStackTrace();
}
			
		reviewService.updateReview(review);
		return "redirect:/userreviewdetail?reviewId=" + review.getReview_id();
	}

	@PostMapping("/review/delete")
	public String deleteReview(@RequestParam("reviewId") int reviewId, HttpSession session) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		ReviewVO review = reviewService.getReviewByReviewId(reviewId);

		if (loginUser == null || !review.getUser_id().equals(loginUser.getUser_id())) {
			return "redirect:/access-denied"; // or show error page
		}

		reviewService.deleteReviewById(reviewId);
		return "redirect:/userreview";
	}

	@PostMapping("/review/insert")
	public String insertReviewById(@ModelAttribute ReviewVO review, @RequestParam("imageFile") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				String uploadPath = "C:/jojonezip/upload/review"; // 운영체제 경로에 맞게

	            File dir = new File(uploadPath);
	            if (!dir.exists()) dir.mkdirs();

	            File saveFile = new File(uploadPath, fileName);
	            file.transferTo(saveFile);

	            review.setReview_image(fileName);

				System.out.println("리뷰 이미지 저장 경로: " + uploadPath + "/" + fileName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		reviewService.insertReviewById(review);
		
		return "redirect:/productdetail?productId=" + review.getProduct_id() + "&tab=review";
	}
}
