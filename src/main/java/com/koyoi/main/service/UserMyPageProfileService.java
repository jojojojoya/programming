package com.koyoi.main.service;

import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.mapper.UserMyPageProfileMapper;
import com.koyoi.main.vo.UserMyPageProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMyPageProfileService {
//일을 하는곳

    @Autowired
    private static UserMyPageProfileMapper userMyPageProfileMapper;

    public static boolean updateProfile(UserMyPageProfileVO userMyPageProfileVO) {
        int updatedRows = userMyPageProfileMapper.updateProfile(userMyPageProfileVO);
        return updatedRows > 0;
}
}
