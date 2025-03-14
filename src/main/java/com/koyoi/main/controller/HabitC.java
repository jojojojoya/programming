package com.koyoi.main.controller;

import com.koyoi.main.service.HabitService;
import com.koyoi.main.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class HabitC {

    @Autowired
    private HabitService habitService;

    @GetMapping("/habit")
    public String habit(Model model) {
        // user_id를 "user1"로 고정
        String userId = "user1";

        // userId에 맞는 습관 목록을 DB에서 가져옴
        List<HabitVO> habits = habitService.getUserHabits(userId);
        model.addAttribute("habits", habits);  // 습관 목록을 JSP로 전달

        return "habit/finalhabit";  // habit/finalhabit.jsp 페이지로 이동
    }

    // 습관 추가 메소드
    @PostMapping("/habit/add")
    public String addHabit(@RequestBody Map<String, String> requestData) {
        // user_id를 "user1"로 고정
        String userId = "user1";
        String habitName = requestData.get("habitName");

        // HabitVO 객체 생성하여 userId와 habitName 설정
        HabitVO habitVO = new HabitVO();
        habitVO.setUser_id(userId);
        habitVO.setHabit_name(habitName);

        // 습관 추가
        habitService.addHabit(habitVO);

        // 추가 후 습관 목록 갱신
        return "redirect:/habit";  // 새로 추가된 습관을 포함한 목록을 다시 로드
    }
}
