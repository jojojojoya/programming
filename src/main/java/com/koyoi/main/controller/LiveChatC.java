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

import java.time.LocalDate;
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


//    @PostMapping("/livechatreservation")
//    public ResponseEntity<Map<String, Object>> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {
//        String userId = (String) session.getAttribute("user_id");
//        if (userId == null || userId.isEmpty()) {
//            userId = "user5";  // 기본 유저 "user5" 유지
//        }
//
//        try {
//            // ✅ 1. 날짜 확인
//            if (request.get("livechatreservedate") == null || request.get("livechatreservedate").isEmpty()) {
//                System.out.println("❌ [오류] 예약 날짜가 비어 있음!");
//                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "날짜가 비어 있습니다."));
//            }
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDate = LocalDate.parse(request.get("livechatreservedate"), formatter);
//            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//
//            // ✅ 2. 시간 확인 및 변환
//            if (request.get("livechatreservetime") == null || request.get("livechatreservetime").isEmpty()) {
//                System.out.println("❌ [오류] 예약 시간이 비어 있음!");
//                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "시간이 비어 있습니다."));
//            }
//
//            String timeString = request.get("livechatreservetime").trim();
//            System.out.println("🔍 [예약 시간 요청] " + timeString);
//
//            int counselingTime;
//            try {
//                counselingTime = Integer.parseInt(timeString.split(":")[0]);  // "23:00" → 23 변환
//            } catch (NumberFormatException e) {
//                System.out.println("❌ [오류] 예약 시간 변환 실패! 잘못된 형식: " + timeString);
//                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "올바른 시간 형식이 아닙니다."));
//            }
//
//            System.out.println("🕒 변환된 예약 시간: " + counselingTime);
//
//            // ✅ 3. 시간 범위 검증 (10~23)
//            if (counselingTime < 10 || counselingTime > 23) {
//                System.out.println("❌ [오류] 유효하지 않은 시간 선택!");
//                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "유효한 시간이 아닙니다."));
//            }
//
//            // ✅ 4. 카테고리 확인
//            String category = request.get("livechatcategory");
//            if (category == null || category.isEmpty()) {
//                System.out.println("❌ [오류] 카테고리가 비어 있음!");
//                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "카테고리가 비어 있습니다."));
//            }
//
//            // ✅ 5. 예약 데이터 생성
//            LiveChatVO reservation = new LiveChatVO();
//            reservation.setUser_id(userId);
//            reservation.setCounseling_date(sqlDate);
//            reservation.setCounseling_time(counselingTime);
//            reservation.setCategory(category);
//            reservation.setStatus("대기");
//            reservation.setCounselor_id("counselor001");
//
//            System.out.println("🔹 [예약 요청] 사용자 ID: " + userId);
//            System.out.println("🔹 예약 날짜: " + sqlDate);
//            System.out.println("🔹 예약 시간: " + counselingTime);
//            System.out.println("🔹 카테고리: " + category);
//
//            // ✅ 6. 상담 예약 시도
//            boolean isReserved = liveChatService.reserveCounseling(reservation);
//
//            if (isReserved) {
//                liveChatService.updateReservationsStatus();
//                System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());
//                return ResponseEntity.ok(Map.of("success", true, "message", "예약이 완료되었습니다."));
//            } else {
//                System.out.println("⚠️ 상담 예약 실패 - DB 오류");
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(Map.of("success", false, "message", "예약에 실패했습니다."));
//            }
//        } catch (Exception e) {
//            System.err.println("🚨 서버 오류 발생: " + e.getMessage());
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
//        }
//    }

    @PostMapping("/livechatreservation")
    public ResponseEntity<Map<String, Object>> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {
        String userId = (String) session.getAttribute("user_id");
        if (userId == null || userId.isEmpty()) {
            userId = "user5";  // 기본 유저 "user5" 유지
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(request.get("livechatreservedate"), formatter);
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

            int counselingTime = Integer.parseInt(request.get("livechatreservetime").split(":")[0]);
            String category = request.get("livechatcategory");

            LiveChatVO reservation = new LiveChatVO();
            reservation.setUser_id(userId);
            reservation.setCounseling_date(sqlDate);
            reservation.setCounseling_time(counselingTime);
            reservation.setCategory(category);
            reservation.setStatus("대기");
            reservation.setCounselor_id("counselor001");

            boolean isReserved = liveChatService.reserveCounseling(reservation);

            if (isReserved) {
//                liveChatService.updateReservationsStatus();
                System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());

                // ✅ 상담 ID도 함께 반환
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "예약이 완료되었습니다.",
                        "counseling_id", reservation.getCounseling_id(),
                        "session_id", reservation.getSession_id()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("success", false, "message", "예약에 실패했습니다."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
        }
    }


    // ✅ 채팅 로그 조회 API
    @GetMapping("/chatlogs")
    public ResponseEntity<List<LiveChatVO>> getChatLogs(@RequestParam int sessionId) {
        if (sessionId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        List<LiveChatVO> chatLogs = liveChatService.getChatLogs(sessionId);
        if (chatLogs.isEmpty()) {
            System.out.println("⚠️ 채팅 로그 없음: session_id=" + sessionId);
        } else {
            System.out.println("💬 채팅 로그 개수: " + chatLogs.size());
        }

        return ResponseEntity.ok(chatLogs);
    }

    // ✅ 채팅 메시지 저장 API
    @PostMapping("/chatmessage")
    public ResponseEntity<Map<String, Object>> saveChatMessage(@RequestBody LiveChatVO message) {
        liveChatService.saveChatMessage(message);
        System.out.println("✅ 채팅 메시지 저장 완료: " + message.getContent());
        return ResponseEntity.ok(Map.of("success", true, "message", "채팅 메시지 저장 완료"));
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

    // ✅ 상담 완료 처리 API
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

    //    @PostMapping("/updateStatus")
//    public ResponseEntity<?> updateStatus(@RequestBody Map<String, Object> request) {
//        try {
//            int counselingId = (int) request.get("counseling_id");
//            String status = (String) request.get("status");
//
//            int result = liveChatService.updateReservationStatus(counselingId, status);
//            if (result > 0) {
//                return ResponseEntity.ok(Map.of("success", true, "message", "상담 상태 업데이트 성공"));
//            } else {
//                return ResponseEntity.ok(Map.of("success", false, "message", "상담 상태 업데이트 실패"));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "error", e.getMessage()));
//        }
//    }
//
//}
//    @PostMapping("/livechat/updateStatus")
//    public ResponseEntity<?> updateStatus(@RequestBody Map<String, Object> request) {
//        try {
//            int counselingId = (int) request.get("counseling_id");
//            String status = (String) request.get("status");
//
//            System.out.println("📌 [서버] 상담 ID: " + counselingId + ", 변경할 상태: " + status);
//
//            if (!status.equals("대기") && !status.equals("취소됨")) {
//                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "잘못된 상태 값입니다."));
//            }
//
//            int result = liveChatService.updateReservationStatus(counselingId, status);
//            if (result > 0) {
//                return ResponseEntity.ok(Map.of("success", true, "message", "상담 상태 업데이트 성공"));
//            } else {
//                return ResponseEntity.ok(Map.of("success", false, "message", "상담 상태 업데이트 실패"));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "error", e.getMessage()));
//        }
//    }}

    @PostMapping("/livechat/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Map<String, Object> request) {
        try {
            Integer counselingId = (Integer) request.get("counseling_id");
            String status = (String) request.get("status");

            if (counselingId == null || status == null) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "상담 ID 또는 상태가 누락되었습니다."));
            }

            System.out.println("📌 [서버] 상담 ID: " + counselingId + ", 변경할 상태: " + status);

            boolean result = liveChatService.updateReservationStatus(counselingId, status);
            if (result) {
                return ResponseEntity.ok(Map.of("success", true, "message", "상담 상태 업데이트 성공"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("success", false, "message", "DB 업데이트 실패"));
            }
        } catch (Exception e) {
            System.err.println("🚨 상담 상태 업데이트 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }
}
