package com.koyoi.main.service;

import com.koyoi.main.mapper.CounselorMyPageMapper;
import com.koyoi.main.vo.CounselorMyPageVO;
import com.koyoi.main.vo.UserMyPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselorMyPageService {

    private final CounselorMyPageMapper counselorMyPageMapper;

    public List<CounselorMyPageVO> getReservationsByCounselorId(String counselor_id) {
        return counselorMyPageMapper.getReservationsByCounselorId(counselor_id);
    }

    public List<CounselorMyPageVO> getCounselorById(String user_id) {
        return counselorMyPageMapper.getCounselorById(user_id);
    }

    public boolean checkPassword(String userId, String inputPassword) {
        String storedPassword = counselorMyPageMapper.getPasswordByUserId(userId);

        System.out.println("입력된 패스워드: " + inputPassword);
        System.out.println("DB에서 가져온 패스워드: " + storedPassword);

        if (storedPassword == null) {
            return false;
        }
        return storedPassword.trim().equals(inputPassword.trim());

    }

    public boolean updateProfile(CounselorMyPageVO user) {
        return counselorMyPageMapper.updateProfile(user) > 0;
    }

//    public List<CounselorMyPageVO> getUserChatBotDetail(String user_id) {
//        return CounselorMyPageMapper.getUserChatBotDetail(user_id);
//    }

}
