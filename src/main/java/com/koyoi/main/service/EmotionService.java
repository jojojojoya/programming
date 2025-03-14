package com.koyoi.main.service;

import com.koyoi.main.mapper.EmotionMapper;
import com.koyoi.main.vo.EmotionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    public List<EmotionVO> getUserAllEmotions(String userId) {
        return emotionMapper.getAllUserEmotions(userId);
    }
}
