package com.koyoi.main.service;

import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.vo.UserMyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.util.List;

@Service
public class UserMyPageService {

        private final DataSource dataSource;

        @Autowired
        public UserMyPageService(DataSource dataSource) {
            this.dataSource = dataSource;
        }


        @Autowired
    private UserMyPageMapper userMyPageMapper;

    public boolean updateProfile(UserMyPageVO user) {
        return userMyPageMapper.updateProfile(user) > 0;
    }

    private String clobToString(Clob clob) {
        try (Reader reader = clob.getCharacterStream();
             BufferedReader br = new BufferedReader(reader)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public List<UserMyPageVO> getUserChatBotDetail(String user_id) {
        List<UserMyPageVO> chatList = userMyPageMapper.getUserChatBotDetail(user_id);

        // Clob을 String으로 변환하여 chat_summary_str에 저장
        for (UserMyPageVO chat : chatList) {
            if (chat.getChat_summary() != null) {
                chat.setChat_summary_str(clobToString(chat.getChat_summary()));
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

    public boolean isNicknameDuplicate(String nickname, String currentUserId) {
        return userMyPageMapper.countByNicknameExcludeCurrentUser(nickname, currentUserId) > 0;
    }

    public int countNicknameExcludeCurrentUser(String nickname, String userId) {
        return userMyPageMapper.countByNicknameExcludeCurrentUser(nickname, userId);
    }

}
