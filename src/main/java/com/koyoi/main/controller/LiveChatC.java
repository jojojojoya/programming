package com.koyoi.main.controller;

import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.vo.LiveChatVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Controller
public class LiveChatC {

    @Autowired
    private LiveChatService liveChatService;

    // ✅ "입장하기" 버튼이 필요한 상담 조회
    @GetMapping("/available")
    public ResponseEntity<List<LiveChatVO>> getAvailableReservations() {
        liveChatService.updateReservationsStatus(); // 상담 상태 업데이트 반영
        List<LiveChatVO> reservations = liveChatService.getAvailableReservations();

        if (reservations.isEmpty()) {
            System.out.println("⚠️ 예약된 상담 없음.");
        } else {
            System.out.println("🔍 예약된 상담 개수: " + reservations.size());
        }

        return ResponseEntity.ok(reservations);
    }

    // ✅ 상담 예약 페이지
    @GetMapping("/livechatreservation")
    public String showLiveChatReservations(Model model) {
//        liveChatService.updateReservationsStatus(); // 상담 상태 최신화
        List<LiveChatVO> availableReservations = liveChatService.getAvailableReservations();
        model.addAttribute("availableReservations", availableReservations);
        return "/livechat/livechatreservation";
    }

    @GetMapping("/livechatdetail")
    public String showLiveChatDetails(@RequestParam(value = "sessionId", required = false) Integer sessionId,
                                      @RequestParam(value = "counselingId", required = false) Integer counselingId,
                                      @RequestParam(value = "isCompleted", required = false, defaultValue = "false") boolean isCompleted,
                                      Model model) {
        System.out.println("🔍 상담 상세 페이지 요청: sessionId=" + sessionId + ", counselingId=" + counselingId + ", isCompleted=" + isCompleted);

//        if (sessionId == null || sessionId <= 0) {
//            System.out.println("❌ 유효하지 않은 sessionId: " + sessionId);
//            return "redirect:/usermypage"; // 상담 ID가 없으면 마이페이지로 리다이렉트
//        }

        // ✅ 상담 정보 가져오기
        LiveChatVO counselingDetail = liveChatService.getCounselingDetail(counselingId);
        System.out.println("counselingDetail : " + counselingDetail);
        if (counselingDetail.getSession_id() == 0) {
            System.out.println("⚠️ 상담 내역 없음: sessionId=" + sessionId);
            counselingDetail.setSession_id(sessionId);
        }

        // ✅ 채팅 로그 가져오기
        List<LiveChatVO> chatLogs = liveChatService.getChatLogs(sessionId);
        if (chatLogs.isEmpty()) {
            System.out.println("⚠️ 채팅 기록 없음: sessionId=" + sessionId);
        }

        model.addAttribute("counseling", counselingDetail);
        model.addAttribute("chatLogs", chatLogs);
        model.addAttribute("isCompleted", isCompleted);

        System.out.println("✅ 상담 상세 페이지 로드 완료: sessionId=" + sessionId + ", isCompleted=" + isCompleted);
        return "/livechat/livechatdetail";
    }

//
//    @PostMapping("/livechatreservation")
//    public ResponseEntity<Map<String, Object>> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {
//        String userId = (String) session.getAttribute("user_id");
//        if (userId == null || userId.isEmpty()) {
//            userId = "user5";  // 기본 유저 "user5" 유지
//        }
//
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDate = LocalDate.parse(request.get("livechatreservedate"), formatter);
//            LocalDateTime sqlDate = Date.valueOf(localDate);
//
//            int counselingTime = Integer.parseInt(request.get("livechatreservetime").split(":")[0]);
//            String category = request.get("livechatcategory");
//
//            LiveChatVO reservation = new LiveChatVO();
//            reservation.setUser_id(userId);
//            reservation.setCounseling_date(sqlDate);
//            reservation.setCounseling_time(counselingTime);
//            reservation.setCategory(category);
//            reservation.setStatus("대기");
//            reservation.setCounselor_id("counselor001");
//
//            boolean isReserved = liveChatService.reserveCounseling(reservation);
//
//            if (isReserved) {
////                liveChatService.updateReservationsStatus();
//                System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());
//
//                // ✅ 상담 ID도 함께 반환
//                return ResponseEntity.ok(Map.of(
//                        "success", true,
//                        "message", "예약이 완료되었습니다.",
//                        "counseling_id", reservation.getCounseling_id(),
//                        "session_id", reservation.getSession_id()
//                ));
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(Map.of("success", false, "message", "예약에 실패했습니다."));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
//        }
//    }
//@PostMapping("/livechatreservation")
//public ResponseEntity<Map<String, Object>> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {
//    String userId = (String) session.getAttribute("user_id");
//    if (userId == null || userId.isEmpty()) {
//        userId = "user5";  // 기본 유저 "user5" 유지
//    }
//
//    try {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(request.get("livechatreservedate"), formatter);
//        Date sqlDate = Date.valueOf(localDate);  // ✅ java.sql.Date로 변환 (LocalDateTime 아님)
//
//        int counselingTime = Integer.parseInt(request.get("livechatreservetime").split(":")[0]);
//        String category = request.get("livechatcategory");
//
//        LiveChatVO reservation = new LiveChatVO();
//        reservation.setUser_id(userId);
//        reservation.setCounseling_date(sqlDate);  // ✅ 수정된 부분
//        reservation.setCounseling_time(counselingTime);
//        reservation.setCategory(category);
//        reservation.setStatus("대기");
//        reservation.setCounselor_id("counselor001");
//
//        boolean isReserved = liveChatService.reserveCounseling(reservation);
//
//        if (isReserved) {
//            System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());
//
//            return ResponseEntity.ok(Map.of(
//                    "success", true,
//                    "message", "예약이 완료되었습니다.",
//                    "counseling_id", reservation.getCounseling_id(),
//                    "session_id", reservation.getSession_id()
//            ));
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "message", "예약에 실패했습니다."));
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
//    }
//}
@PostMapping("/livechatreservation")
public ResponseEntity<Map<String, Object>> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {
    String userId = (String) session.getAttribute("user_id");
    if (userId == null || userId.isEmpty()) {
        userId = "user5";  // 기본 유저 설정
    }

    try {
        // ✅ 1. 요청값 검증 (Null 체크)
        String dateString = request.get("livechatreservedate");
        String timeString = request.get("livechatreservetime");
        String category = request.get("livechatcategory");

        if (dateString == null || timeString == null || category == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "❌ 날짜, 시간, 카테고리는 필수 입력값입니다."
            ));
        }

        System.out.println("📌 [서버] 예약 요청 데이터: 날짜=" + dateString + ", 시간=" + timeString + ", 카테고리=" + category);

        // ✅ 2. 날짜 변환 오류 방지
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "❌ 날짜 형식이 올바르지 않습니다. (yyyy-MM-dd 형식 필요)"
            ));
        }

        Date sqlDate = Date.valueOf(localDate);

        // ✅ 3. 시간 변환 오류 방지
        int counselingTime;
        try {
            counselingTime = Integer.parseInt(timeString.split(":")[0]);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "❌ 상담 시간 형식이 올바르지 않습니다. (예: 15:00)"
            ));
        }

        // ✅ 4. LiveChatVO 객체 생성
        LiveChatVO reservation = new LiveChatVO();
        reservation.setUser_id(userId);
        reservation.setCounseling_date(sqlDate);
        reservation.setCounseling_time(counselingTime);
        reservation.setCategory(category);
        reservation.setStatus("대기");
        reservation.setCounselor_id("counselor001");

        // ✅ 5. 예약 처리
        boolean isReserved = liveChatService.reserveCounseling(reservation);

        if (isReserved) {
            System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "예약이 완료되었습니다.",
                    "counseling_id", reservation.getCounseling_id(),
                    "session_id", reservation.getSession_id()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "❌ 상담 예약에 실패했습니다."));
        }

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", "🚨 서버 오류 발생: " + e.getMessage()));
    }
}



    // ✅ 채팅 로그 조회 API
//    @GetMapping("/chatlogs")
//    public ResponseEntity<List<LiveChatVO>> getChatLogs(@RequestParam int sessionId) {
//        List<LiveChatVO> chatLogs = liveChatService.getChatLogs(sessionId);
//        return ResponseEntity.ok(chatLogs);
//    }


    // 특정 세션의 채팅 내역 가져오기
    @GetMapping("/chatlogs/{sessionId}")
    public List<LiveChatVO> getChatLogs(@PathVariable int sessionId) {
        return liveChatService.getChatLogs(sessionId);
    }

//
//
//
//    // ✅ JSP로 해석되지 않도록 @ResponseBody 추가
//    @PostMapping("/chatmessage") // ⬅️ 경로 변경
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> saveChatMessage(@RequestBody LiveChatVO message) {
//        try {
//            liveChatService.saveChatMessage(message);
//            System.out.println("✅ [백엔드] 채팅 메시지 저장 완료: " + message.getMessage());
//
//            return ResponseEntity.ok(Map.of("success", true, "message", "채팅 메시지 저장 완료"));
//        } catch (Exception e) {
//            System.err.println("🚨 [백엔드 오류] 채팅 메시지 저장 실패: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "message", "DB 저장 실패"));
//        }
//    }

    @PostMapping("/chatmessage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveChatMessage(@RequestBody LiveChatVO message) {
        try {
            System.out.println("📩 [백엔드] 저장 요청 받은 메시지: " + message);
            if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
                System.err.println("🚨 [백엔드 오류] message 필드가 null 또는 비어 있음!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("success", false, "message", "메시지가 비어 있습니다."));
            }

            liveChatService.saveChatMessage(message);
            System.out.println("✅ [백엔드] 채팅 메시지 저장 완료: " + message.getMessage());

            return ResponseEntity.ok(Map.of("success", true, "message", "채팅 메시지 저장 완료"));
        } catch (Exception e) {
            System.err.println("🚨 [백엔드 오류] 채팅 메시지 저장 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "DB 저장 실패"));
        }
    }


    @GetMapping("/updateWaitingStatus")
    public ResponseEntity<Map<String, Object>> updateWaitingStatus() {
        liveChatService.updateWaitingStatus();
        return ResponseEntity.ok(Map.of("success", true, "message", "대기 상태 업데이트 완료"));
    }

    // ✅ 상담 상태 업데이트 (완료 상태)
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
            System.out.println("✅ 상담 완료: " + counselingId);
            return ResponseEntity.ok(Map.of("success", true, "message", "상담이 완료되었습니다.", "updatedCount", updatedCount));
        } else {
            System.out.println("⚠️ 상담 완료 실패: " + counselingId);
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
                response.put("message", "❌ 상담 ID가 없습니다.");
                return response;
            }

            System.out.println("📌 [백엔드] 상담 상태 업데이트 요청 - counselingId: " + counselingId + ", status: " + status);

            boolean isUpdated = liveChatService.updateReservationStatus(counselingId, status);

            if (isUpdated) {
                response.put("success", true);
                response.put("message", "✅ 상담 상태 업데이트 완료!");
            } else {
                response.put("success", false);
                response.put("message", "⚠️ 상담 상태 업데이트 실패 (DB 반영 안 됨)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "🚨 서버 오류: " + e.getMessage());
        }

        return response;
    }

    /// /세션아이디 저장
//@PostMapping("/livechat/complete")
//public ResponseEntity<Map<String, Object>> completeCounselingBySession(@RequestBody Map<String, Object> request) {
//    Integer sessionId = (Integer) request.get("session_id");
//    String summary = (String) request.get("summary"); // 채팅 요약 데이터
//
//    if (sessionId == null || sessionId <= 0) {
//        return ResponseEntity.badRequest().body(Map.of("success", false, "message", "잘못된 세션 ID"));
//    }
//
//    System.out.println("📌 [서버] 상담 완료 요청 - sessionId: " + sessionId + ", summary: " + summary);
//
//    // sessionId로 counselingId 찾기
//    Integer counselingId = liveChatService.findCounselingIdBySession(sessionId);
//    if (counselingId == null) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(Map.of("success", false, "message", "해당 sessionId에 대한 상담 정보 없음"));
//    }
//
//    // 상담을 완료 상태로 변경
//    int updatedCount = liveChatService.completeCounseling(counselingId);
//
//    if (updatedCount > 0) {
//        System.out.println("✅ 상담 완료 - counselingId: " + counselingId);
//
//        // 채팅 요약 저장 (Optional)
//        liveChatService.saveChatSummary(sessionId, summary);
//
//
//
//
//        return ResponseEntity.ok(Map.of("success", true, "message", "상담이 완료되었습니다.", "updatedCount", updatedCount));
//    } else {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Map.of("success", false, "message", "DB 업데이트 실패"));
//    }
//}}
//    @PostMapping("/livechat/complete")
//    @ResponseBody
//    public Map<String, Object> completeChat(@RequestBody Map<String, Object> requestData) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            Integer sessionId = (Integer) requestData.get("session_id");
//            String summary = (String) requestData.get("summary");
//
//            System.out.println("📌 [DEBUG] 상담 종료 요청 - sessionId: " + sessionId + ", summary: " + summary);
//
//            if (sessionId == null) {
//                response.put("success", false);
//                response.put("message", "❌ 세션 ID가 없습니다.");
//                return response;
//            }
//
//            // 상담 로그 저장 로직
//            int updatedRows = liveChatService.completeChat(sessionId, summary);
//
//            if (updatedRows > 0) {
//                response.put("success", true);
//            } else {
//                response.put("success", false);
//                response.put("message", "❌ 상담 종료 업데이트 실패 (DB 반영되지 않음)");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.put("success", false);
//            response.put("message", "🚨 서버 오류: " + e.getMessage());
//        }
//
//        return response;
//    }}

@PostMapping("/livechat/complete")
@ResponseBody
public Map<String, Object> completeChat(@RequestBody Map<String, Object> requestData) {
    Map<String, Object> response = new HashMap<>();

    try {
        Integer sessionId = (Integer) requestData.get("session_id");

        if (sessionId == null) {
            response.put("success", false);
            response.put("message", "❌ 세션 ID가 없습니다.");
            return response;
        }

        liveChatService.completeChat(sessionId);

        response.put("success", true);
        response.put("message", "✅ 상담 종료 완료!");
    } catch (Exception e) {
        e.printStackTrace();
        response.put("success", false);
        response.put("message", "🚨 서버 오류: " + e.getMessage());
    }

    return response;
}

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
            response.put("message", "🚨 서버 오류: " + e.getMessage());
        }

        return response;
    }

    @PostMapping("/livechat/updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer counselingId = (Integer) request.get("counseling_id");
            String status = (String) request.get("status");

            if (counselingId == null || status == null) {
                response.put("success", false);
                response.put("message", "❌ 유효하지 않은 요청 값입니다.");
                return response;
            }

            boolean isUpdated = liveChatService.updateReservationStatus(counselingId, status);

            if (isUpdated) {
                response.put("success", true);
                response.put("message", "✅ 상태 업데이트 성공!");
            } else {
                response.put("success", false);
                response.put("message", "❌ 상태 업데이트 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "🚨 서버 오류: " + e.getMessage());
        }
        return response;
    }

}