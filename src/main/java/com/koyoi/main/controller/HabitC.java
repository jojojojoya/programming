package com.koyoi.main.controller;


import com.koyoi.main.service.HabitService;
import com.koyoi.main.vo.HabitVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HabitC {

    @Autowired
    private HabitService habitService;

    @GetMapping("/habit")
    public String habit(HttpSession session, Model model) {
        // 세션에서 로그인한 사용자 ID 가져오기
        String userId = (String) session.getAttribute("userId");



        // userId에 맞는 습관 목록을 DB에서 가져옴
        List<HabitVO> habits = habitService.getUserHabits(userId);
        model.addAttribute("habits", habits);  // 습관 목록을 JSP로 전달

        return "habit/finalhabit";  // habit/finalhabit.jsp 페이지로 이동
    }
}
