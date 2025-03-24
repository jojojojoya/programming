package com.koyoi.main.mapper;

import com.koyoi.main.vo.EmotionVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmotionMapper {

    // 감정 데이터 등록 (emotion_emoji 포함)
    @Insert("INSERT INTO TEST_EMOTION (user_id, diary_id, emotion_emoji, emotion_score, recorded_at) " +
            "VALUES (#{user_id}, #{diary_id}, #{emotion_emoji}, #{emotion_score}, #{recorded_at})")
    int addEmotion(EmotionVO emotionVO);

    // 감정 이모지와 recorded_at 동시 수정
    @Update("UPDATE TEST_EMOTION " +
            "SET emotion_emoji = #{emotion_emoji}, " +
            "    recorded_at = #{recorded_at} " +
            "WHERE diary_id = #{diary_id} " +
            "AND user_id = #{user_id}")
    int updateEmotionWithDate(@Param("diary_id") int diaryId,
                              @Param("emotion_emoji") String emotionEmoji,
                              @Param("recorded_at") LocalDateTime recordedAt,
                              @Param("user_id") String userId);

    // 오늘의 점수만 수정 (모달에서 점수 입력 후 호출)
    @Update("UPDATE TEST_EMOTION " +
            "SET emotion_score = #{emotion_score} " +
            "WHERE diary_id = #{diary_id} " +
            "AND user_id = #{user_id}")
    int updateEmotionScore(@Param("diary_id") int diaryId,
                           @Param("emotion_score") int emotionScore,
                           @Param("user_id") String userId);

    // 오늘의 점수 삭제
    @Delete("DELETE FROM TEST_EMOTION " +
            "WHERE diary_id = #{diaryId} " +
            "AND user_id = #{userId}")
    int deleteEmotion(@Param("diaryId") int diaryId, @Param("userId") String userId);

    // 달력 이모지
    @Select("SELECT E.emotion_id, E.user_id, E.diary_id, E.emotion_score, E.emotion_emoji, E.recorded_at " +
            "FROM TEST_EMOTION E LEFT JOIN TEST_USER U ON E.user_id = U.user_id " +
            "LEFT JOIN TEST_DIARY D ON E.diary_id = D.diary_id " +
            "WHERE E.user_id = #{userId} " +
            "ORDER BY E.recorded_at ASC")
    List<EmotionVO> getAllUserEmotions(String userId);

    // 무드 그래프
    @Select("SELECT E.emotion_id, E.user_id, E.diary_id, E.emotion_score, E.emotion_emoji, E.recorded_at " +
            "FROM TEST_EMOTION E LEFT JOIN TEST_USER U ON E.user_id = U.user_id " +
            "LEFT JOIN TEST_DIARY D ON E.diary_id = D.diary_id " +
            "WHERE E.user_id = #{userId} " +
            "AND TRUNC(E.recorded_at) BETWEEN " +
            "TO_DATE(#{startDate}, 'YYYY-MM-DD') AND TO_DATE(#{endDate}, 'YYYY-MM-DD') " +
            "ORDER BY E.recorded_at ASC")
    List<EmotionVO> getWeeklyMoodScores(String userId, String startDate, String endDate);
}
