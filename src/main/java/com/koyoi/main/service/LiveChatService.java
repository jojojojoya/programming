package com.koyoi.main.service;

import com.koyoi.main.mapper.LiveChatMapper;
import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LiveChatService {

    @Autowired
    private LiveChatMapper liveChatMapper;

    @Autowired
    private UserMyPageMapper userMyPageMapper;

    public String findRandomCounselor() {
        return liveChatMapper.findRandomCounselor();
    }

    @Transactional
    public boolean reserveCounseling(LiveChatVO reservation) {
        try {
            // 부모 테이블 (TEST_COUNSELING_RESERVATION)에 상담 예약 먼저 저장
            int result = liveChatMapper.reserveCounseling(reservation);

            if (result > 0) {
                System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());

                // DB에 실제로 예약이 저장되었는지 확인 후 session_id 생성
                Integer counselingId = reservation.getCounseling_id();
                if (counselingId == null || counselingId <= 0) {
                    throw new RuntimeException("🚨 예약된 상담 ID(counseling_id)가 존재하지 않습니다.");
                }

                // 상담 ID를 이용하여 채팅방 생성 (자식 테이블)
                reservation.setStart_time(reservation.getCounseling_time());
                Integer sessionId = liveChatMapper.createChatRoom(reservation);

                if (sessionId == null || sessionId <= 0) {
                    throw new RuntimeException("🚨 세션 ID 생성 실패!");
                }

                reservation.setSession_id(sessionId);
                return true;
            } else {
                System.out.println("⚠️ 상담 예약 실패!");
                return false;
            }
        } catch (Exception e) {
            System.err.println("🚨 상담 예약 중 오류 발생: " + e.getMessage());
            return false;
        }
    }


    // 예약된 상담 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<LiveChatVO> getAvailableReservations() {
        List<LiveChatVO> reservations = liveChatMapper.findAvailableReservations();
        if (reservations.isEmpty()) {
            System.out.println("⚠️ 예약된 상담 없음.");
        } else {
            System.out.println("🔍 예약된 상담 개수: " + reservations.size());
        }
        return reservations;
    }

    // 완료된 상담 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<LiveChatVO> getCompletedReservations() {
        return liveChatMapper.findCompletedReservations();
    }

    // 상담 ID로 상담 내역 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public LiveChatVO getCounselingDetail(int counselingId) {
        return liveChatMapper.findReservationById(counselingId);
    }

    // 상담 ID로 채팅 내역 가져오기 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<LiveChatVO> getChatLogs(int sessionId) {
        if (sessionId <= 0) {
            System.out.println("⚠️ 유효하지 않은 session_id: " + sessionId);
            return List.of();
        }

        List<LiveChatVO> chatLogs = liveChatMapper.getChatLogs(sessionId);
        if (chatLogs.isEmpty()) {
            System.out.println("⚠️ 채팅 로그 없음: session_id=" + sessionId);
        } else {
            System.out.println("💬 채팅 로그 개수: " + chatLogs.size());
        }
        return chatLogs;
    }

    // 실시간 채팅 메시지 저장
    @Transactional
    public void saveChatMessage(LiveChatVO message) {
        try {
            if (message == null) {
                System.err.println("🚨 [오류] 저장할 메시지가 null 입니다.");
                return;
            }

            System.out.println("📩 [백엔드] 채팅 메시지 저장 시도: " + message);

            if (message.getSession_id() == null || message.getSession_id() <= 0) {
                System.err.println("🚨 [오류] session_id가 유효하지 않음: " + message.getSession_id());
                return;
            }

            if (message.getSender() == null || message.getSender().trim().isEmpty()) {
                System.err.println("🚨 [오류] sender 값이 비어있음");
                return;
            }

            if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
                System.err.println("🚨 [오류] message 값이 비어있음");
                return;
            }

            liveChatMapper.insertChatMessage(message);
            System.out.println("✅ [백엔드] 채팅 메시지 저장 완료: " + message.getMessage());

        } catch (Exception e) {
            System.err.println("🚨 [DB 오류] 채팅 메시지 저장 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 특정 상담 완료 처리
    @Transactional
    public int completeCounseling(Integer counselingId) {
        try {
            int updatedCount = liveChatMapper.completeCounseling(counselingId);
            if (updatedCount > 0) {
                System.out.println("✅ 상담 완료: " + counselingId);
            } else {
                System.out.println("⚠️ 상담 완료 실패: " + counselingId);
            }
            return updatedCount;
        } catch (Exception e) {
            System.err.println("🚨 상담 완료 처리 중 오류 발생: " + e.getMessage());
            return 0;
        }
    }

    // 특정 상담의 상태를 '대기'로 변경
    @Transactional
    public void updateReservationStatusToWaiting(int counselingId) {
        try {
            int updated = liveChatMapper.updateReservationStatus(counselingId, "대기");
            if (updated > 0) {
                System.out.println("✅ 상담 상태를 '대기'로 변경 완료: counseling_id=" + counselingId);
            } else {
                System.out.println("⚠️ 상담 상태 변경 실패 (조건 불충족): counseling_id=" + counselingId);
            }
        } catch (Exception e) {
            System.err.println("🚨 상담 상태 변경 중 오류 발생: " + e.getMessage());
        }
    }

    //예약 내역 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<UserMyPageVO> getUserReservations(String userId) {
        return userMyPageMapper.getUserReservations(userId);
    }

    // 전체 상담을 '대기' 상태로 업데이트하는 기능
    @Transactional
    public void updateWaitingStatus() {
        try {
            int updatedToWaiting = liveChatMapper.updateToWaitingStatus();
            System.out.println("🔄 전체 상담 '대기' 상태 업데이트: " + updatedToWaiting + "건");
        } catch (Exception e) {
            System.err.println("🚨 '대기' 상태 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    // 완료된 상담 업데이트 기능
    @Transactional
    public void updateCompletedStatus() {
        try {
            int updatedToCompleted = liveChatMapper.completeCounseling(null);
            System.out.println("🔄 완료된 상담 업데이트: " + updatedToCompleted + "건");
        } catch (Exception e) {
            System.err.println("🚨 '완료' 상태 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    // 자동으로 상태 업데이트 (시간 지남)

    @Transactional
    public boolean updateReservationStatus(int counselingId, String status) {
        System.out.println("🔎 [백엔드] updateReservationStatus 실행 - 상담 ID: " + counselingId + ", 상태: " + status);

        int updatedRows = userMyPageMapper.updateCounselingStatus(counselingId, status);

        if (updatedRows == 0) {
            System.err.println("❌ [백엔드] DB 업데이트 실패 - 상담 ID(" + counselingId + ")가 존재하지 않거나 상태값 오류");
        } else {
            System.out.println("✅ [백엔드] 상담 상태 업데이트 완료 - 변경된 행 수: " + updatedRows);
        }

        return updatedRows > 0;
    }

    
    //사용자가 상담 종료하기 (나가기) 누를때 상태변경
    public void updateReservationsStatus() {
    List<UserMyPageVO> allReservations = userMyPageMapper.getAllReservations(); // 모든 예약 가져오기

    LocalDateTime now = LocalDateTime.now();

    for (UserMyPageVO reservation : allReservations) {
        // 날짜 + 시간 → LocalDateTime
        LocalDateTime counselingTime = reservation.getCounseling_date().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .atTime(reservation.getCounseling_time(), 0);

        if ("대기".equals(reservation.getStatus()) && counselingTime.isBefore(now)) {
            userMyPageMapper.updateCounselingStatus(reservation.getCounseling_id(), "완료");
            System.out.println("🔁 상태 자동 업데이트 완료: 상담 ID " + reservation.getCounseling_id());
        }
    }
}


    // (나가기 버튼 누를 시 ) 세션아이디 기반 추가

    @Transactional(readOnly = true)
    public Integer findCounselingIdBySession(int sessionId) {
        return liveChatMapper.findCounselingIdBySession(sessionId);
    }

    // 로그인 유저 상단 프로필 띄우기
    public List<UserMyPageVO> getUserInfoById(String userId) {
        return userMyPageMapper.getUserById(userId); // 이미 MyPage에서 쓰고 있던 메서드
    }


    // 특정 상담 종료 처리
    @Transactional
    public void completeChat(int sessionId) {
        liveChatMapper.completeChat(sessionId);
    }
}
