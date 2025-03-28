package com.koyoi.main.controller;

import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Controller
public class LiveChatC {


    private String getLoginUserId(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return userId;
    }

    // 라이브챗 서비스 담당
    @Autowired
    private LiveChatService liveChatService;

    @GetMapping("/available")
    public ResponseEntity<List<LiveChatVO>> getAvailableReservations() {
        liveChatService.updateReservationsStatus(); // 상담 상태 업데이트 반영
        List<LiveChatVO> reservations = liveChatService.getAvailableReservations();

        if (reservations.isEmpty()) {
            // 예약 상담 없음
            System.out.println("⚠️ 예약된 상담 없음.");
        } else {
            // 예약 상담 개수
            System.out.println("🔍 예약된 상담 개수: " + reservations.size());
        }

        return ResponseEntity.ok(reservations);
    }



    //  상담 예약 페이지 진입
    @GetMapping("/livechatreservation")
    public String showLiveChatReservations(Model model, HttpSession session) {
        // 로그인된 유저 가져오기
        String userId = getLoginUserId(session);
        UserMyPageVO loggedInUser = liveChatService.getUserInfoById(userId).get(0);




        // 예약 가능한 상담 목록 추가
        List<LiveChatVO> availableReservations = liveChatService.getAvailableReservations();

        // 모델에 추가
        model.addAttribute("availableReservations", availableReservations);
        model.addAttribute("user", loggedInUser); // 로그인된 유저 객체를 user에 담고 ${user.user_img}로 사용
        model.addAttribute("livechatreservation", "livechat/livechatreservation.jsp");
        return "/finalindex";
    }


    // 라이브 상담 디테일 보기
    @GetMapping("/livechatdetail")
    public String showLiveChatDetails(@RequestParam(value = "sessionId", required = false) Integer sessionId,
                                      @RequestParam(value = "counselingId", required = false) Integer counselingId,
                                      @RequestParam(value = "isCompleted", required = false, defaultValue = "false") boolean isCompleted,
                                      Model model, HttpSession session) {

        String userId = getLoginUserId(session);
        List<UserMyPageVO> userList = liveChatService.getUserInfoById(userId);
        UserMyPageVO loggedInUser = userList.isEmpty() ? null : userList.get(0);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("userType", loggedInUser.getUser_type()); // ← 이게 JSP에 전달됨

        model.addAttribute("livechatdetail", "livechat/livechatdetail.jsp");

        LiveChatVO counselingDetail = liveChatService.getCounselingDetail(counselingId);
        System.out.println("counselingDetail : " + counselingDetail);
        if (counselingDetail.getSession_id() == 0) {
            System.out.println("상담 내역 없음: sessionId=" + sessionId);
            counselingDetail.setSession_id(sessionId);
        }

        // 채팅 로그 가져오기
        List<LiveChatVO> chatLogs = liveChatService.getChatLogs(sessionId);
        if (chatLogs.isEmpty()) {
            System.out.println("채팅 기록 없음: sessionId=" + sessionId);
        }

        model.addAttribute("counseling", counselingDetail);

        model.addAttribute("chatLogs", chatLogs);

        model.addAttribute("isCompleted", isCompleted);


        System.out.println("상담 상세 페이지 로드 완료: sessionId=" + sessionId + ", isCompleted=" + isCompleted);
        return "/finalindex";

    }




    @PostMapping("/livechatreservation")
    public ResponseEntity<Map<String, Object>> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {

        String userId = getLoginUserId(session);



        try {
        // 요청값 검증 (Null 체크)
        String dateString = request.get("livechatreservedate");
        String timeString = request.get("livechatreservetime");
        String category = request.get("livechatcategory");

        if (dateString == null || timeString == null || category == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "날짜, 시간, 카테고리는 필수 입력값입니다."
            ));
        }
            category = category.trim(); // 앞뒤 공백 제거
            if (category.equals("sonota")) {
                category = "その他のお悩み";
            }

        System.out.println("[서버] 예약 요청 데이터: 날짜=" + dateString + ", 시간=" + timeString + ", 카테고리=" + category);

        // 날짜 변환 오류 방지 (formatter에 패턴 담기)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "날짜 형식이 올바르지 않습니다. (yyyy-MM-dd 형식 필요)"
            ));
        }

        Date sqlDate = Date.valueOf(localDate);

        // 시간 변환 오류 방지
        int counselingTime;
        try {
            counselingTime = Integer.parseInt(timeString.split(":")[0]);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "상담 시간 형식이 올바르지 않습니다. (예: 15:00)"
            ));
        }

        // LiveChatVO 객체 생성
         LiveChatVO reservation = new LiveChatVO();
            reservation.setUser_id(userId);
            reservation.setCounseling_date(sqlDate);
            reservation.setCounseling_time(counselingTime);
            reservation.setCategory(category);
            reservation.setStatus("待機中");
            System.out.println("카테고리 확인: [" + category + "]");

            String randomCounselorId = liveChatService.findRandomCounselor(); // 랜덤 상담사 배정
            reservation.setCounselor_id(randomCounselorId); // 배정한 상담사 ID 설정


        // 예약 처리
        boolean isReserved = liveChatService.reserveCounseling(reservation);
        System.out.println("user_id: [" + reservation.getUser_id() + "]");
        System.out.println("counselor_id: [" + reservation.getCounselor_id() + "]");

        if (isReserved) {
            System.out.println("상담 예약 성공: " + reservation.getCounseling_id());
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "예약이 완료되었습니다.",
                    "counseling_id", reservation.getCounseling_id(),
                    "session_id", reservation.getSession_id()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "상담 예약에 실패했습니다."));
        }

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", "서버 오류 발생: " + e.getMessage()));
    }
}







    // 특정 채팅방 세션 id로 채팅 내역 가져오기
    @GetMapping("/chatlogs/{sessionId}")
    public List<LiveChatVO> getChatLogs(@PathVariable int sessionId) {
        return liveChatService.getChatLogs(sessionId);
    }


    // 특정 채팅방 세션 id의 채팅 내역 저장하기
    @PostMapping("/chatmessage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveChatMessage(@RequestBody LiveChatVO message) {
        try {
            System.out.println("[백엔드] 저장 요청 받은 메시지: " + message);
            if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
                System.err.println("[백엔드 오류] message 필드가 null 또는 비어 있음!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("success", false, "message", "메시지가 비어 있습니다."));
            }

            liveChatService.saveChatMessage(message);
            System.out.println("[백엔드] 채팅 메시지 저장 완료: " + message.getMessage());

            return ResponseEntity.ok(Map.of("success", true, "message", "채팅 메시지 저장 완료"));
        } catch (Exception e) {
            System.err.println("[백엔드 오류] 채팅 메시지 저장 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "DB 저장 실패"));
        }
    }


    @GetMapping("/updateWaitingStatus")
    public ResponseEntity<Map<String, Object>> updateWaitingStatus() {
        liveChatService.updateWaitingStatus();
        return ResponseEntity.ok(Map.of("success", true, "message", "대기 상태 업데이트 완료"));
    }

    // 상담 상태 업데이트 (완료 상태)
    @GetMapping("/updateCompletedStatus")
    public ResponseEntity<Map<String, Object>> updateCompletedStatus() {
        liveChatService.updateCompletedStatus();
        return ResponseEntity.ok(Map.of("success", true, "message", "완료 상태 업데이트 완료"));
    }

    // livechat detail에서 나가기 버튼 누를 시 작동
    @PostMapping("/completeCounseling")
    public ResponseEntity<Map<String, Object>> completeCounseling(@RequestBody Map<String, Integer> request) {
        Integer counselingId = request.get("counseling_id");

        if (counselingId == null || counselingId <= 0) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "잘못된 상담 ID"));
        }

        int updatedCount = liveChatService.completeCounseling(counselingId);

        if (updatedCount > 0) {
            System.out.println("상담 완료: " + counselingId);
            return ResponseEntity.ok(Map.of("success", true, "message", "상담이 완료되었습니다.", "updatedCount", updatedCount));
        } else {
            System.out.println("상담 완료 실패: " + counselingId);
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "DB 업데이트 실패"));
        }
    }

    //livechat_exit_btn (상담예약에서 나가기 버튼과 연결)
    @ResponseBody
    public Map<String, Object> updateChatStatus(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer counselingId = (Integer) requestData.get("counseling_id");
            String status = "완료"; // 상담 종료 시 상태를 "완료"로 설정

            if (counselingId == null || counselingId <= 0) {
                response.put("success", false);
                response.put("message", "상담 ID가 없습니다.");
                return response;
            }

            System.out.println("[백엔드] 상담 상태 업데이트 요청 - counselingId: " + counselingId + ", status: " + status);

            boolean isUpdated = liveChatService.updateReservationStatus(counselingId, status);

            if (isUpdated) {
                response.put("success", true);
                response.put("message", "상담 상태 업데이트 완료!");
            } else {
                response.put("success", false);
                response.put("message", "상담 상태 업데이트 실패 (DB 반영 안 됨)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
        }

        return response;
    }

// 상담 종료하기 누를 시 
@PostMapping("/livechat/complete")
@ResponseBody
public Map<String, Object> completeChat(@RequestBody Map<String, Object> requestData) {
    Map<String, Object> response = new HashMap<>();

    try {
        Integer sessionId = (Integer) requestData.get("session_id");

        if (sessionId == null) {
            response.put("success", false);
            response.put("message", "세션 ID가 없습니다.");
            return response;
        }

        liveChatService.completeChat(sessionId);

        response.put("success", true);
        response.put("message", "상담 종료 완료!");
    } catch (Exception e) {
        e.printStackTrace();
        response.put("success", false);
        response.put("message", "서버 오류: " + e.getMessage());
    }

    return response;
}

    // 세션 id로 해당 채팅 내역을 조회
    @GetMapping("/livechat/getCounselingId")
    @ResponseBody
    public Map<String, Object> getCounselingIdBySession(@RequestParam int sessionId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer counselingId = liveChatService.findCounselingIdBySession(sessionId);

            if (counselingId == null) {
                response.put("success", false);
                response.put("message", "해당 세션에 대한 상담 정보 없음");
            } else {
                response.put("success", true);
                response.put("counseling_id", counselingId);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
        }

        return response;
    }
    
    // 상담 상태 업데이트된 버전을 post
    @PostMapping("/livechat/updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer counselingId = (Integer) request.get("counseling_id");
            String status = (String) request.get("status");

            if (counselingId == null || status == null) {
                response.put("success", false);
                response.put("message", "유효하지 않은 요청 값입니다.");
                return response;
            }

            boolean isUpdated = liveChatService.updateReservationStatus(counselingId, status);

            if (isUpdated) {
                response.put("success", true);
                response.put("message", "상태 업데이트 성공!");
            } else {
                response.put("success", false);
                response.put("message", "상태 업데이트 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/chatmessage/checkWelcome")
    @ResponseBody
    public Map<String, Object> checkWelcome(@RequestParam("sessionId") int sessionId) {
        boolean exists = liveChatService.existsWelcomeMessage(sessionId);
        Map<String, Object> result = new HashMap<>();
        result.put("exists", exists);
        return result;
    }

}