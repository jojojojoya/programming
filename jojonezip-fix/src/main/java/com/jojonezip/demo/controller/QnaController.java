package com.jojonezip.demo.controller;

import java.security.PublicKey;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.service.QnaService;
import com.jojonezip.demo.vo.QnaVO;
import com.jojonezip.demo.vo.ReviewVO;
import com.jojonezip.demo.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class QnaController {
	
	
	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("/userqnadetail")
	public String showqnadetails(@RequestParam("qnaId") int qnaId, HttpSession session, Model model) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/loginpage";
        }
        
        QnaVO qna = qnaService.getQnaByQnaId(qnaId);
        model.addAttribute("qnaDetail",qna);
        return "userqnadetail/userqnadetail";
	}
	
	@PostMapping("/qna/delete") 
		public String deleteqna(@RequestParam("qnaId") int qnaId, HttpSession session) {
			UserVO loginUser = (UserVO) session.getAttribute("loginUser");
			QnaVO qna = qnaService.getQnaByQnaId(qnaId);
			
			if (loginUser == null || !loginUser.getUser_id().equals(qna.getUser_id()) )  {
				return "redirect:/access-denied"; // or show error page
        }

        qnaService.deleteQnaById(qnaId);
        return "redirect:/userqna";

			
	}
	 @GetMapping("/qna/updateForm")
	    public String showQnaUpdateForm(@RequestParam("qnaId") int qnaId, Model model) {
	    	QnaVO qna = qnaService.getQnaByQnaId(qnaId);
	    	model.addAttribute("qna",qna);
	    	return "userqnadetail/userqnaupdate";
	    }
	
	 @PostMapping("/qna/update")
	 public String updeteqna(QnaVO qna) {
		 qnaService.updateQnaById(qna);
		 return "redirect:/userqnadetail?qnaId=" + qna.getQna_id();
		 
	 }
	 
	 @PostMapping("/qna/insert")
	 public String insertQna(QnaVO qna) {
		 qnaService.insertQnaById(qna);
		 return "redirect:/productdetail?productId=" + qna.getProduct_id()+ "&tab=qna";
	 }
}
	
