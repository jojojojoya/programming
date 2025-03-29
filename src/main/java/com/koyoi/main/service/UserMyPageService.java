package com.koyoi.main.service;

import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.vo.UserMyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserMyPageService {


        @Autowired
    private UserMyPageMapper userMyPageMapper;

    public boolean updateProfile(UserMyPageVO user) {
        return userMyPageMapper.updateProfile(user) > 0;
    }


public List<UserMyPageVO> getUserChatBotDetail(String user_id) {
    List<UserMyPageVO> chatList = userMyPageMapper.getUserChatBotDetail(user_id);

    for (UserMyPageVO chat : chatList) {
        if (chat.getChat_summary() != null) {
        }
    }

    return chatList;
}


    public List<UserMyPageVO> getUserReservations(String user_id) {
        return userMyPageMapper.getUserReservations(user_id);
    }

    public List<UserMyPageVO> getUserById(String user_id) {
        return userMyPageMapper.getUserById(user_id);
    }

    public boolean checkPassword(String userId, String inputPassword) {
        String storedPassword = userMyPageMapper.getPasswordByUserId(userId);

        System.out.println("입력된 패스워드: " + inputPassword);
        System.out.println("DB에서 가져온 패스워드: " + storedPassword);


        if (storedPassword == null) {
            return false;



        }
        return storedPassword.trim().equals(inputPassword.trim());

    }


    public int countNicknameExcludeCurrentUser(String nickname, String userId) {
        return userMyPageMapper.countByNicknameExcludeCurrentUser(nickname, userId);
    }

}
