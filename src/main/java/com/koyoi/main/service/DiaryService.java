package com.koyoi.main.service;

import com.koyoi.main.mapper.DiaryMapper;
import com.koyoi.main.mapper.EmotionMapper;
import com.koyoi.main.vo.DiaryVO;
import com.koyoi.main.vo.EmotionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Map<String, Object>> getDiaryEvents(String userId) {
        List<Map<String, Object>> diaries = diaryMapper.getDiaryEmojisForCalendar(userId);
        List<Map<String, Object>> events = new ArrayList<>();

        for (Map<String, Object> diary : diaries) {
            Map<String, Object> event = new HashMap<>();
            event.put("title", diary.get("EMOTION_EMOJI")); // 이모지 출력
            event.put("start", diary.get("DIARY_DATE"));    // 날짜 출력

            Map<String, Object> extendedProps = new HashMap<>();
            extendedProps.put("diary_id", diary.get("DIARY_ID"));
            event.put("extendedProps", extendedProps);

            events.add(event);
        }
        return events;
    }

    // 일기 상세 조회 (diaryId 기준)
    public DiaryVO getDiaryById(int diaryId) {
        return diaryMapper.getDiaryById(diaryId);
    }

    // 일기 + 감정 등록
    @Transactional
    public void saveDiary(DiaryVO diaryVO) {
        // 일기 저장
        diaryMapper.addDiary(diaryVO);

        // 감정 저장 (일기 저장 후 ID 필요)
        EmotionVO emotionVO = new EmotionVO();
        emotionVO.setDiary_id(diaryVO.getDiary_id());
        emotionVO.setUser_id(diaryVO.getUser_id());
        emotionVO.setEmotion_emoji(diaryVO.getEmotion_emoji());
        emotionVO.setEmotion_score(0); // 기본 점수는 0 → 나중에 업데이트 가능

        emotionMapper.addEmotion(emotionVO);

        System.out.println("[DiaryService] 다이어리 + 감정 등록 완료 - diaryId: " + diaryVO.getDiary_id());
    }

    // 일기 수정 (일기 + 감정 둘 다 수정)
    public void updateDiary(DiaryVO diaryVO) {
        // 일기 내용 수정
        diaryMapper.updateDiary(diaryVO);

        // 감정 이모지 수정 (감정 점수는 별도로 관리)
        emotionMapper.updateEmotion(diaryVO.getDiary_id(), diaryVO.getEmotion_emoji());

        System.out.println("[DiaryService] 일기 수정 완료 - diaryId: " + diaryVO.getDiary_id());
    }

    // 일기 삭제 (감정 + 일기 순으로 삭제)
    public void deleteDiary(int diaryId) {
        // 감정 정보 삭제
        emotionMapper.deleteEmotion(diaryId);

        // 일기 삭제
        diaryMapper.deleteDiary(diaryId);

        System.out.println("[DiaryService] 일기 삭제 완료 - diaryId: " + diaryId);
    }

    // 감정 점수 저장 (PUT /diary/emotion/score 호출 시 사용)
    public void saveEmotionScore(int diaryId, int emotionScore) {
        emotionMapper.updateEmotionScore(diaryId, emotionScore);

        System.out.println("[DiaryService] 감정 점수 저장 완료 - diaryId: " + diaryId + ", score: " + emotionScore);
    }
}
