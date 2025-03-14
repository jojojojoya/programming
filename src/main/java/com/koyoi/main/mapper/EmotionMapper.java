package com.koyoi.main.mapper;

import com.koyoi.main.vo.EmotionVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmotionMapper {
    // 특정 날짜의 감정 데이터 조회
    @Select("SELECT * FROM TEST_EMOTION WHERE diary_id = #{diaryId}")
    EmotionVO getEmotionByDiaryId(int diaryId);

    // 감정 데이터 등록
    @Insert("INSERT INTO TEST_EMOTION (emotion_id, user_id, diary_id, emotion_score, recorded_at) " +
            "VALUES ( #{user_id}, #{diary_id}, #{emotion_score}, SYSDATE)")
    int addEmotion(EmotionVO emotionVO);

//    // 감정 데이터 업데이트 (모달 이후)
//    @Update("UPDATE TEST_EMOTION SET emotion_score = #{emotion_score} WHERE diary_id = #{diary_id}")
//    int updateEmotionScore(EmotionVO emotionVO);
//

//    // 감정 데이터 삭제 (다이어리 삭제 시 함께 삭제)
//    @Delete("DELETE FROM TEST_EMOTION WHERE diary_id = #{diaryId}")
//    int deleteEmotionByDiaryId(int diaryId);

    @Select("SELECT E.emotion_id, E.user_id, E.diary_id, E.emotion_score, E.emotion_emoji, E.recorded_at " +
            "FROM TEST_EMOTION E LEFT JOIN TEST_USER U ON E.user_id = U.user_id " +
            "LEFT JOIN TEST_DIARY D ON E.diary_id = D.diary_id WHERE E.user_id = #{userId} ORDER BY E.recorded_at ASC")
    List<EmotionVO> getAllUserEmotions(String userId);


}
