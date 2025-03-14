package com.koyoi.main.service;

import com.koyoi.main.mapper.DiaryMapper;
import com.koyoi.main.mapper.EmotionMapper;
import com.koyoi.main.vo.DiaryVO;
import com.koyoi.main.vo.EmotionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryMapper diaryMapper;
    private final EmotionMapper emotionMapper;

    // 캘린더에 이모지 이벤트 반환
    public List<Map<String, Object>> getDiaryEvents() {
//        String userId = "userId";
        List<Map<String, Object>> diaries = diaryMapper.getDiaryEmojisForCalendar();


        List<Map<String, Object>> events = new ArrayList<>();
        for (Map<String, Object> diary : diaries) {
            Map<String, Object> event = new HashMap<>();
            event.put("title", diary.get("EMOTION_EMOJI")); // 캘린더 타이틀 (이모지)
            event.put("start", diary.get("DIARY_DATE"));    // 날짜
            events.add(event);
        }

        return events;
    }


    // 날짜별 일기 조회
    public DiaryVO getDiaryById(int diaryId) {
        return diaryMapper.getDiaryById(diaryId);
    }

    // 다이어리 + 감정 등록
    public void addDiaryAndEmotion(DiaryVO diaryVO) {
        diaryMapper.addDiary(diaryVO);

        EmotionVO emotionVO = new EmotionVO();
        emotionVO.setDiary_id(diaryVO.getDiary_id());
        emotionVO.setUser_id(diaryVO.getUser_id());
        emotionVO.setEmotion_emoji(diaryVO.getEmotion_emoji());
        emotionVO.setEmotion_score(0);
        emotionMapper.addEmotion(emotionVO);
    }
}

