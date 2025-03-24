package com.koyoi.main.service;

import com.koyoi.main.mapper.DiaryMapper;
import com.koyoi.main.mapper.EmotionMapper;
import com.koyoi.main.vo.DiaryVO;
import com.koyoi.main.vo.EmotionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryMapper diaryMapper;
    private final EmotionMapper emotionMapper;
    private final DataSource dataSource;

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
        DiaryVO diary = diaryMapper.getDiaryById(diaryId);
        System.out.println("[DiaryService] 조회된 DiaryVO: " + diary);

        return diary;
    }

    // 일기 조회 (날짜기준)
    public DiaryVO getDiaryByDate(String userId, LocalDateTime date) {
        String dateStr = date.toLocalDate().toString();

        System.out.println("✅ DiaryService.getDiaryByDate() → 날짜 기준 조회 date: " + dateStr);

        return diaryMapper.getDiaryByDate(userId, dateStr);
    }

    // 일기 + 감정 등록
    @Transactional
    public void saveDiary(DiaryVO diaryVO) {
        // 필수 값 검증
        if (diaryVO.getUser_id() == null || diaryVO.getUser_id().isEmpty()) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }

        System.out.println("[saveDiary] 받은 diaryVO 값: " + diaryVO);

        // 일기 저장
        diaryMapper.addDiary(diaryVO);
        System.out.println("[saveDiary] 일기 저장 완료");
        LocalDateTime createdAtStr = diaryVO.getCreated_at();

        Integer diaryId = diaryMapper.findDiaryId(
                diaryVO.getUser_id(),
                diaryVO.getTitle(),
                createdAtStr
        );
        System.out.println("[saveDiary] 조회된 diary_id: " + diaryId);

        if (diaryId == null) {
            throw new IllegalStateException("일기 저장 후 diary_id를 찾을 수 없습니다.");
        }

        diaryVO.setDiary_id(diaryId);

        // 감정 저장
        EmotionVO emotionVO = new EmotionVO();
        emotionVO.setDiary_id(diaryId);
        emotionVO.setUser_id(diaryVO.getUser_id());
        emotionVO.setEmotion_emoji(diaryVO.getEmotion_emoji());
        emotionVO.setEmotion_score(0);
        emotionVO.setRecorded_at(diaryVO.getCreated_at());
        emotionMapper.addEmotion(emotionVO);

        System.out.println("[DiaryService] 다이어리 + 감정 등록 완료 - diaryId: " + diaryId);
    }

    // 일기 수정 (일기 + 감정 둘 다 수정)
    @Transactional
    public void updateDiaryAndEmotion(DiaryVO diaryVO) {
        System.out.println("[updateDiary] 수정 요청 받은 diaryVO: " + diaryVO);

        // 사용자 검증
        DiaryVO originDiary = diaryMapper.getDiaryById(diaryVO.getDiary_id());
        if (originDiary == null) {
            throw new IllegalArgumentException("수정할 일기가 존재하지 않습니다.");
        }

        if (!originDiary.getUser_id().equals(diaryVO.getUser_id())) {
            throw new SecurityException("본인의 일기만 수정 가능합니다.");
        }

        // 일기 수정
        int diaryUpdateResult = diaryMapper.updateDiary(diaryVO);
        if (diaryUpdateResult == 0) {
            throw new IllegalStateException("일기 수정 실패! diary_id: " + diaryVO.getDiary_id());
        }

        // emotionMapper 수정
        int emotionUpdateResult = emotionMapper.updateEmotionWithDate(
                diaryVO.getDiary_id(),
                diaryVO.getEmotion_emoji(),
                diaryVO.getCreated_at(),  // recorded_at 값
                diaryVO.getUser_id()
        );

        if (emotionUpdateResult == 0) {
            throw new IllegalStateException("감정 수정 실패! diary_id: " + diaryVO.getDiary_id());
        }

        System.out.println("[updateDiaryAndEmotion] 일기 + 감정 수정 완료! diary_id: " + diaryVO.getDiary_id());
    }

    // 일기 삭제 (감정 + 일기 순으로 삭제)

    public void deleteDiary(int diaryId, String userId) {
        DiaryVO diary = diaryMapper.getDiaryById(diaryId);

        if (diary == null) {
            throw new IllegalArgumentException("삭제할 일기가 존재하지 않습니다.");
        }

        if (!diary.getUser_id().equals(userId)) {
            throw new SecurityException("본인의 일기만 삭제 가능합니다.");
        }

        emotionMapper.deleteEmotion(diaryId, userId);
        diaryMapper.deleteDiary(diaryId, userId);
        System.out.println("[DiaryService] 일기 삭제 완료 - diaryId: " + diaryId);
    }

    // 감정 점수 저장
    public void saveEmotionScore(int diaryId, int emotionScore, String userId) {
        DiaryVO diary = diaryMapper.getDiaryById(diaryId);

        if (diary == null) {
            throw new IllegalArgumentException("감정 점수를 저장할 일기가 존재하지 않습니다.");
        }

        if (!diary.getUser_id().equals(userId)) {
            throw new SecurityException("본인의 일기에만 감정 점수를 저장할 수 있습니다.");
        }

        emotionMapper.updateEmotionScore(diaryId, emotionScore, userId);

        System.out.println("[DiaryService] 감정 점수 저장 완료 - diaryId: " + diaryId + ", score: " + emotionScore);
    }

}
