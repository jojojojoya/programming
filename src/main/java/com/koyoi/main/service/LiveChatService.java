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
import java.util.stream.Collectors;

@Service
public class LiveChatService {

    @Autowired
    private LiveChatMapper liveChatMapper;

    public boolean reserveLiveChat(LiveChatVO chat) {
        return liveChatMapper.reserveLiveChat(chat) >0;
    }
    @Transactional
    public boolean reserveCounseling(LiveChatVO reservation) {
        return liveChatMapper.reserveCounseling(reservation) > 0;
    }

    public List<LiveChatVO> getUserLiveChats(String userId) {
        if (userId == null) {
            userId = "user5";
        }
        return liveChatMapper.getUserLiveChats(userId);
    }

    public LiveChatService(LiveChatMapper liveChatMapper) {
        this.liveChatMapper = liveChatMapper;
    }

    public List<LiveChatVO> getAvailableReservations() {
        return liveChatMapper.findAvailableReservations();
    }


    public List<LiveChatVO> getCompletedReservations() {
        List<LiveChatVO> completedList = liveChatMapper.findCompletedReservations();
        return completedList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    // 예약된 상담을 완료 상태로 변경하는 스케줄러 (매 1분마다 실행)
    @Scheduled(fixedRate = 60000) // 60초(1분)마다 실행
    @Transactional
    public void updateCompletedReservations() {
        int updatedCount = liveChatMapper.updateCompletedReservations();
        System.out.println("완료된 상담 업데이트 개수: " + updatedCount);
    }

}





