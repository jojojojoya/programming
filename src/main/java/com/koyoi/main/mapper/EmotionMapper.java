package com.koyoi.main.mapper;

import com.koyoi.main.vo.EmotionVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmotionMapper {
    // 감정 데이터 등록 (emotion_emoji 포함)
    @Insert("INSERT INTO TEST_EMOTION (emotion_id, user_id, diary_id, emotion_emoji, emotion_score, recorded_at) " +
            "VALUES (TEST_EMOTION_SEQ.NEXTVAL, #{user_id}, #{diary_id}, #{emotion_emoji}, #{emotion_score}, SYSDATE)")
    int addEmotion(EmotionVO emotionVO);

    // 감정 이모지만 수정 (일기 수정 시 호출)
    @Update("UPDATE TEST_EMOTION SET emotion_emoji = #{emotion_emoji} WHERE diary_id = #{diary_id}")
    int updateEmotion(@Param("diary_id") int diaryId, @Param("emotion_emoji") String emotionEmoji);

    // 오늘의 점수만 수정 (모달에서 점수 입력 후 호출)
    @Update("UPDATE TEST_EMOTION SET emotion_score = #{emotion_score} WHERE diary_id = #{diary_id}")
    int updateEmotionScore(@Param("diary_id") int diaryId, @Param("emotion_score") int emotionScore);

    // 오늘의 점수 삭제
    @Delete("DELETE FROM TEST_EMOTION WHERE diary_id = #{diaryId}")
    int deleteEmotion(@Param("diaryId") int diaryId);
    
    // 메인페이지 무드그래프
    @Select("SELECT E.emotion_id, E.user_id, E.diary_id, E.emotion_score, E.emotion_emoji, E.recorded_at " +
            "FROM TEST_EMOTION E LEFT JOIN TEST_USER U ON E.user_id = U.user_id " +
            "LEFT JOIN TEST_DIARY D ON E.diary_id = D.diary_id WHERE E.user_id = #{userId} ORDER BY E.recorded_at ASC")
    List<EmotionVO> getAllUserEmotions(String userId);


}
