package com.koyoi.main.controller;

import com.koyoi.main.service.AdminMypageService;
import com.koyoi.main.service.HabitService;
import com.koyoi.main.vo.AdminMypageVO;
import com.koyoi.main.vo.HabitTrackingVO;
import com.koyoi.main.vo.HabitVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/habit")
@Controller
public class HabitC {

    @Autowired
    private HabitService habitService;
    @Autowired
    private AdminMypageService adminMypageService;


    @GetMapping("")
    public String habit(HttpSession session ,Model model) {
        // user_id를 "user1"로 고정
        //String userId = "user1";
        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }


        List<HabitVO> habits = habitService.getUserHabits(userId);
        model.addAttribute("habits", habits);

        model.addAttribute("finalhabit", "habit/finalhabit.jsp");

        return "finalindex";  // 전체 레이아웃 페이지로 이동
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> addHabit(HttpSession session, @RequestBody HabitVO habitVO, Model model) {
//        //String userId = "user1";  // 테스트용
//// 로그인 세션
//        String userId = (String) session.getAttribute("userId");
//
//        if (userId != null) {
//            AdminMypageVO user = adminMypageService.getUserById(userId);
//            model.addAttribute("user", user);
//        }
//        try {
//            System.out.println("📥 습관 추가 요청: " + habitVO); // ✅ 로그 찍어보기
//            habitVO.setUser_id(userId);  // 로그인한 사용자 ID 주입
//            HabitVO newHabit = habitService.addHabit(habitVO);
//            return ResponseEntity.status(HttpStatus.OK).body(newHabit);
//
//        } catch (Exception e) {
//            e.printStackTrace(); // ✅ 반드시 콘솔에 에러 출력되도록!
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("습관 추가 실패");
//        }
//   }

@PostMapping("/add")
public ResponseEntity<?> addHabit(HttpSession session, @RequestBody HabitVO habitVO, Model model) {
    String userId = (String) session.getAttribute("userId");

    if (userId != null) {
        AdminMypageVO user = adminMypageService.getUserById(userId);
        model.addAttribute("user", user);
    }

    try {
        System.out.println("📥 습관 추가 요청: " + habitVO);
        habitVO.setUser_id(userId);

        HabitVO newHabit = habitService.addHabit(habitVO);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "habit_id", newHabit.getHabit_id()
        ));

    } catch (RuntimeException e) {
        // ✅ 중복 습관일 경우 - 메시지 명시적으로 전달
        if (e.getMessage() != null && e.getMessage().contains("중복")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", "duplicate",
                    "message", "すでに登録された習慣です"
            ));
        }

        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", "error",
                "message", "習慣の追加に失敗しました"
        ));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", "error",
                "message", "서버 에러로 인해 실패했습니다"
        ));
    }
}



//    @PostMapping("/addNewHabit")
//// ❌ 아래 줄 삭제!!
//// public Map<String, Object> addNewHabit(@RequestParam String habitName, @SessionAttribute("user_id") String userId)
//
//// ✅ 대신 아래 코드로 변경
//    public ResponseEntity<?> addNewHabit(@RequestBody Map<String, String> request) {
//        System.out.println("📌 [DEBUG] /addNewHabit 엔드포인트 호출됨!");  // ✅ 컨트롤러 실행 확인용 로그
//        String userId = "user1";  // ✅ user_id를 "user1"로 고정
//        String habitName = request.get("habitName");
//
//        System.out.println("📌 [DEBUG] 받은 데이터: " + request);
//        System.out.println("📌 [DEBUG] habitName: " + habitName);
//
//
//        if (habitName == null || habitName.trim().isEmpty()) {
//            return ResponseEntity.badRequest().body("습관 이름을 입력하세요!");
//        }
//
//    /*    try {
//            habitService.addNewHabit(userId, habitName);
//            return ResponseEntity.ok("습관이 성공적으로 추가되었습니다.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("습관 추가 중 오류 발생!");
//        }*/
//
//        try {
//            habitService.addNewHabit(userId, habitName);
//            // 성공 시 JSON 형태로 응답
//            return ResponseEntity.ok(Map.of(
//                    "status", "success",
//                    "message", "습관이 성공적으로 추가되었습니다."
//            ));
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 에러 시도 JSON 형태로 응답
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
//                    "status", "error",
//                    "message", "습관 추가 중 오류 발생!"
//            ));
//        }
//
//    }
//

//@PostMapping("/addNewHabit")
//public ResponseEntity<?> addNewHabit(@RequestBody Map<String, String> request) {
//    String habitName = request.get("habit_name");
//    String userId = "user1"; // 테스트용 유저 ID
//
//    System.out.println("📌 [DEBUG] 받은 habit_name: " + habitName);
//
//    // VO 생성 및 세팅
//    HabitVO habit = new HabitVO();
//    habit.setHabit_name(habitName);
//    habit.setUser_id(userId);
//
//    try {
//        habitService.addNewHabit(habit);  // 서비스로 VO 전달
//        return ResponseEntity.ok(Map.of(
//                "status", "success",
//                "message", "습관이 성공적으로 추가되었습니다."
//        ));
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
//                "status", "error",
//                "message", "습관 추가 중 오류 발생!"
//        ));
//    }
//}


    @DeleteMapping("/delete/{habitId}")
    @ResponseBody
    public String deleteHabit(HttpSession session, @PathVariable int habitId, Model model) {

        //String userId = "user1";  // user_id를 "user1"로 고정
        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }
        System.out.println("삭제 요청 - userId: " + userId + ", habitId: " + habitId); // 로깅 추가

        // 해당 습관을 삭제하는 서비스 호출
        boolean success = habitService.deleteHabit(userId, habitId);

        if (success) {
            return "{\"status\":\"success\"}";
        } else {
            return "{\"status\":\"fail\"}";
        }
    }









//    @PostMapping("/addHabitWithTracking")
//    @ResponseBody
//    public ResponseEntity<?> addHabitWithTracking(@RequestBody HabitTrackingVO vo) {
//        System.out.println("📥 받은 VO: " + vo);
//        System.out.println("📌 habit_name: " + vo.getHabit_name());
//        System.out.println("📌 tracking_date: " + vo.getTracking_date());
//        try {
//            // habit_id와 tracking_id 둘 다 생성 및 insert
//            habitService.addHabitWithTracking(vo);
//
//            return ResponseEntity.ok(Map.of(
//                    "status", "success",
//                    "message", "습관 + 추적 정보 등록 성공"
//            ));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
//                    "status", "error",
//                    "message", "습관 등록 중 오류 발생!"
//            ));
//        }
//    }



    // ✅ 해당 날짜의 완료된 습관 목록 조회 (체크 상태 표시용)
    @GetMapping("/tracking/status")
    @ResponseBody
    public List<Integer> getCompletedHabits(HttpSession session , @RequestParam String date, Model model) {
        //String userId = "user1"; // 로그인 사용자로 대체 가능
        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }

        return habitService.getCompletedHabitIds(userId, date);
    }

    // ✅ 습관 체크/해제 저장
    @PostMapping("/tracking")
    @ResponseBody
    public ResponseEntity<?> saveHabitTracking(HttpSession session, @RequestBody HabitTrackingVO vo, Model model) {
        //String userId = "user1"; // 로그인 사용자로 대체 가능
        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }
        vo.setUser_id(userId);

        try {
            System.out.println("📥 받은 Tracking VO: " + vo); // ✅ 로그 찍자
            habitService.saveOrUpdateTracking(vo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // ✅ 로그 꼭 남겨줘야 함!
            return ResponseEntity.status(500).body("저장 실패");
        }
    }

//    @GetMapping("/week/status")
//    @ResponseBody
//    public ResponseEntity<?> getWeeklyStatus(@RequestParam String date) {
//        String userId = "user1"; // 로그인 유저로 대체 가능
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date selectedDate = sdf.parse(date);
//
//            List<Map<String, Object>> result = habitService.getWeeklySummary(userId, selectedDate);
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("주간 정보 조회 실패");
//        }
//    }

    @GetMapping("/week/status")
    @ResponseBody
    public ResponseEntity<?> getWeeklyStatus(HttpSession session, @RequestParam(required = false) String date, Model model) {
        //String userId = "user1"; // 로그인 유저로 대체 가능
        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }

        if (date == null || date.trim().isEmpty() || "null".equals(date)) {
            return ResponseEntity.badRequest().body("유효한 날짜가 전달되지 않았습니다.");
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = sdf.parse(date);

            List<Map<String, Object>> result = habitService.getWeeklySummary(userId, selectedDate);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("주간 정보 조회 실패");
        }
    }

    // ✅ 회고 메모 저장 (insert 또는 update)
    @PostMapping("/memo")
    @ResponseBody
    public ResponseEntity<?> saveWeeklyFeedback(HttpSession session, @RequestBody Map<String, Object> payload, Model model) {
//        String userId = "user1"; // 테스트용 고정 유저

        // 로그인 세션
        //String userId = (String) session.getAttribute("userId");
        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }

        String dateStr = (String) payload.get("tracking_date");
        String feedback = (String) payload.get("feedback");

        // debug
        System.out.println(dateStr);
        System.out.println(feedback);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date trackingDate = sdf.parse(dateStr);

            habitService.saveWeeklyFeedback(userId, trackingDate, feedback);
            return ResponseEntity.ok("저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("메모 저장 실패");
        }
    }

    // ✅ 회고 메모 조회 (일요일 기준)
    @GetMapping("/memo")
    @ResponseBody
    public ResponseEntity<?> getWeeklyFeedback(@RequestParam String date, @RequestParam String user_id) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date trackingDate = sdf.parse(date);

            String feedback = habitService.getWeeklyFeedback(user_id, trackingDate);
            return ResponseEntity.ok(feedback != null ? feedback : "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("메모 조회 실패");
        }
    }



}


