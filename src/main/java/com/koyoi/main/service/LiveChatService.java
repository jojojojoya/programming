package com.koyoi.main.service;

import com.koyoi.main.mapper.LiveChatMapper;
import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LiveChatService {

    @Autowired
    private LiveChatMapper liveChatMapper;

    @Autowired
    private UserMyPageMapper userMyPageMapper;

    // ✅ 상담 예약 저장 (트랜잭션 적용)
    public boolean reserveCounseling(LiveChatVO reservation) {
        try {
            int result = liveChatMapper.reserveCounseling(reservation);
            if (result > 0) {
                System.out.println("✅ 상담 예약 성공: " + reservation.getCounseling_id());
                System.out.println(reservation);
                reservation.setStart_time(reservation.getCounseling_time());
                Integer sessionId = liveChatMapper.createChatRoom(reservation);
                System.out.println("sessionId =====>" + sessionId);
                reservation.setSession_id(sessionId); // session_id
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

    // ✅ 예약된 상담 조회 (읽기 전용 트랜잭션)
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

    // ✅ 완료된 상담 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<LiveChatVO> getCompletedReservations() {
        return liveChatMapper.findCompletedReservations();
    }

    // ✅ 상담 ID로 상담 내역 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public LiveChatVO getCounselingDetail(int counselingId) {
        return liveChatMapper.findReservationById(counselingId);
    }

    // ✅ 상담 ID로 채팅 내역 가져오기 (읽기 전용 트랜잭션)
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

    // ✅ 실시간 채팅 메시지 저장
    @Transactional
    public void saveChatMessage(LiveChatVO message) {
        try {
            int result = liveChatMapper.insertChatMessage(message);
            if (result > 0) {
                System.out.println("✅ 채팅 메시지 저장 완료: " + message.getContent());
            } else {
                System.out.println("⚠️ 채팅 메시지 저장 실패!");
            }
        } catch (Exception e) {
            System.err.println("🚨 채팅 메시지 저장 중 오류 발생: " + e.getMessage());
        }
    }

//    @Scheduled(fixedRate = 60000)
//    @Transactional
    public void updateReservationsStatus() {
        try {
//            int updatedToWaiting = liveChatMapper.updateToWaitingStatus();
//            int updatedToCompleted = liveChatMapper.completeCounseling(null);

//            System.out.println("🔄 상담 상태 업데이트 실행됨");
//            System.out.println("▶ '대기'로 변경된 상담 개수: " + updatedToWaiting);
//            System.out.println("▶ '완료'로 변경된 상담 개수: " + updatedToCompleted);
        } catch (Exception e) {
            System.err.println("🚨 상담 상태 업데이트 중 오류 발생: " + e.getMessage());
        }
    }


    // ✅ 특정 상담 완료 처리
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

    // ✅ 특정 상담의 상태를 '대기'로 변경
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

    // ✅ 예약 내역 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<UserMyPageVO> getUserReservations(String userId) {
        return userMyPageMapper.getUserReservations(userId);
    }

    // ✅ **추가: 전체 상담을 '대기' 상태로 업데이트하는 기능**
    @Transactional
    public void updateWaitingStatus() {
        try {
            int updatedToWaiting = liveChatMapper.updateToWaitingStatus();
            System.out.println("🔄 전체 상담 '대기' 상태 업데이트: " + updatedToWaiting + "건");
        } catch (Exception e) {
            System.err.println("🚨 '대기' 상태 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    // ✅ **추가: 완료된 상담 업데이트 기능**
    @Transactional
    public void updateCompletedStatus() {
        try {
            int updatedToCompleted = liveChatMapper.completeCounseling(null);
            System.out.println("🔄 완료된 상담 업데이트: " + updatedToCompleted + "건");
        } catch (Exception e) {
            System.err.println("🚨 '완료' 상태 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    // ✅ 특정 상담 상태 변경

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
}