package com.koyoi.main.controller;

import com.koyoi.main.service.HabitService;
import com.koyoi.main.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<?> addHabit(@RequestBody HabitVO habitVO) {
        String userId = "user1";
        try {
            // HabitService를 통해 습관을 추가하고, 추가된 습관 객체를 반환
            HabitVO newHabit = habitService.addHabit(habitVO);

            // 성공적으로 추가되면 새 습관 객체를 반환하고 200 OK 상태 반환
            return ResponseEntity.status(HttpStatus.OK).body(newHabit);
        } catch (Exception e) {
            // 에러 발생 시, 내부 서버 오류 상태와 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("습관 추가 실패");
        }
    }


    @DeleteMapping("/habit/delete/{habitId}")
    @ResponseBody
    public String deleteHabit(@PathVariable int habitId) {
        String userId = "user1";  // user_id를 "user1"로 고정
        System.out.println("삭제 요청 - userId: " + userId + ", habitId: " + habitId); // 로깅 추가

        // 해당 습관을 삭제하는 서비스 호출
        boolean success = habitService.deleteHabit(userId, habitId);

        if (success) {
            return "{\"status\":\"success\"}";
        } else {
            return "{\"status\":\"fail\"}";
        }
    }

}


