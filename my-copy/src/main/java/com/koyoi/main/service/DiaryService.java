package com.koyoi.main.service;

import com.koyoi.main.mapper.DiaryMapper;
import com.koyoi.main.mapper.EmotionMapper;
import com.koyoi.main.vo.DiaryVO;
import com.koyoi.main.vo.EmotionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
            event.put("title", diary.get("EMOTION_EMOJI"));
            event.put("start", diary.get("DIARY_DATE"));

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

    // 일기 조회 (날짜기준)
    public DiaryVO getDiaryByDate(String userId, LocalDateTime date) {
        String dateStr = date.toLocalDate().toString();
        return diaryMapper.getDiaryByDate(userId, dateStr);
    }

    // 주간 조회
    public List<DiaryVO> getWeeklyDiaries(String userId, LocalDate selectedDate) {
        LocalDate start = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate end = start.plusDays(6);
        return diaryMapper.getWeeklyDiaries(userId, start.toString(), end.toString());
    }

    // 일기 + 감정 등록
    @Transactional
    public void saveDiary(DiaryVO diaryVO) {
        // 필수 값 검증
        if (diaryVO.getUser_id() == null || diaryVO.getUser_id().isEmpty()) {
            throw new IllegalArgumentException("ユーザー情報が見つかりません。");
        }
        // 일기 저장
        diaryMapper.addDiary(diaryVO);
        LocalDateTime createdAtStr = diaryVO.getCreated_at();

        Integer diaryId = diaryMapper.findDiaryId(
                diaryVO.getUser_id(),
                diaryVO.getTitle(),
                createdAtStr
        );

        if (diaryId == null) {
            throw new IllegalStateException("こよいの保存後、IDを取得できませんでした。");
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
    }

    // 일기 수정 (일기 + 감정 둘 다 수정)
    @Transactional
    public void updateDiaryAndEmotion(DiaryVO diaryVO) {
        // 사용자 검증
        DiaryVO originDiary = diaryMapper.getDiaryById(diaryVO.getDiary_id());
        if (originDiary == null) {
            throw new IllegalArgumentException("編集するこよいが存在しません。");
        }

        if (!originDiary.getUser_id().equals(diaryVO.getUser_id())) {
            throw new SecurityException("自分のこよいのみ編集できます。");
        }

        // 일기 수정
        int diaryUpdateResult = diaryMapper.updateDiary(diaryVO);
        if (diaryUpdateResult == 0) {
            throw new IllegalStateException("こよいの編集に失敗しました (diary_id: " + diaryVO.getDiary_id());
        }

        // emotionMapper 수정
        int emotionUpdateResult = emotionMapper.updateEmotionWithDate(
                diaryVO.getDiary_id(),
                diaryVO.getEmotion_emoji(),
                diaryVO.getCreated_at(),  // recorded_at 값
                diaryVO.getUser_id()
        );

        if (emotionUpdateResult == 0) {
            throw new IllegalStateException("感情の編集に失敗しました (diary_id: " + diaryVO.getDiary_id());
        }

    }

    // 일기 삭제 (감정 → 일기 순으로 삭제)
    public void deleteDiary(int diaryId, String userId) {
        DiaryVO diary = diaryMapper.getDiaryById(diaryId);

        if (diary == null) {
            throw new IllegalArgumentException("削除するこよいが存在しません。");
        }

        if (!diary.getUser_id().equals(userId)) {
            throw new SecurityException("自分のこよいのみ削除できます。");
        }

        emotionMapper.deleteEmotion(diaryId, userId);
        diaryMapper.deleteDiary(diaryId, userId);
    }

    // 감정 점수 저장
    public void saveEmotionScore(int diaryId, int emotionScore, String userId) {
        DiaryVO diary = diaryMapper.getDiaryById(diaryId);

        if (diary == null) {
            throw new IllegalArgumentException("スコアを登録するこよいが存在しません。");
        }

        if (!diary.getUser_id().equals(userId)) {
            throw new SecurityException("自分のこよいにのみスコアを登録できます。");
        }
        emotionMapper.updateEmotionScore(diaryId, emotionScore, userId);
    }

}
