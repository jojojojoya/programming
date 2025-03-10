package com.koyoi.main.service;

import com.koyoi.main.mapper.SampleMapper;
import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.vo.UserMyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMyPageService {
//일을 하는곳
    @Autowired
   UserMyPageMapper userMyPageMapper;

//    public List<UserMyPageVO> getUserInfo() {
//        return UserMyPageMapper.getUserInfo();
//    }


}
