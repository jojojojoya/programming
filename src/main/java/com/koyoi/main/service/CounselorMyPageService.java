package com.koyoi.main.service;

import com.koyoi.main.mapper.CounselorMyPageMapper;
import com.koyoi.main.vo.CounselorMyPageVO;
import com.koyoi.main.vo.UserMyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class CounselorMyPageService {

    private final DataSource dataSource;
    @Autowired
    private CounselorMyPageMapper counselorMyPageMapper;

    @Autowired
    public CounselorMyPageService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Autowired
    private CounselorMyPageMapper CounselorMyPageMapper;

    public boolean updateProfile(CounselorMyPageVO user) {
        return CounselorMyPageMapper.updateProfile(user) > 0;
    }

    public List<CounselorMyPageVO> getUserChatBotDetail(String user_id) {
        List<CounselorMyPageVO> chatList = CounselorMyPageMapper.getUserChatBotDetail(user_id);

        for (CounselorMyPageVO chat : chatList) {
            if (chat.getChat_summary() != null) {
            }
        }

        return chatList;
    }
    public List<CounselorMyPageVO> getUserReservations(String user_id) {
        return CounselorMyPageMapper.getUserReservations(user_id);
    }

    public List<CounselorMyPageVO> getUserById(String user_id) {
        return CounselorMyPageMapper.getUserById(user_id);
    }

    public boolean checkPassword(String userId, String inputPassword) {
        String storedPassword = CounselorMyPageMapper.getPasswordByUserId(userId);

        System.out.println("입력된 패스워드: " + inputPassword);
        System.out.println("DB에서 가져온 패스워드: " + storedPassword);


        if (storedPassword == null) {
            return false;



        }
        return storedPassword.trim().equals(inputPassword.trim());

    }

    public boolean isNicknameDuplicate(String nickname, String currentUserId) {
        return CounselorMyPageMapper.countByNicknameExcludeCurrentUser(nickname, currentUserId) > 0;
    }

    public int countNicknameExcludeCurrentUser(String nickname, String userId) {
        return CounselorMyPageMapper.countByNicknameExcludeCurrentUser(nickname, userId);
    }

    public List<CounselorMyPageVO> getReservationsByCounselorId(String counselor_id) {
        return CounselorMyPageMapper.getReservationsByCounselorId(counselor_id);
    }

}
