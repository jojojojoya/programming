package com.koyoi.main.mapper;

import com.koyoi.main.vo.DiaryVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiaryMapper {
    // 캘린더 조회 (Emotion과 join)
    @Select("SELECT TO_CHAR(D.created_at, 'YYYY-MM-DD') AS DIARY_DATE, " +
            "E.emotion_emoji AS EMOTION_EMOJI " +
            "FROM TEST_DIARY D " +
            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.emotion_id " +  //나중에 diary_id로 통일
            "WHERE D.user_id = 'user2' " +
            "ORDER BY D.created_at DESC")
    List<Map<String, Object>> getDiaryEmojisForCalendar();



//    @Select("SELECT TO_CHAR(D.created_at, 'YYYY-MM-DD') AS diary_date, " +
//            "E.emotion_emoji " +
//            "FROM TEST_DIARY D " +
//            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.diary_id " +
//            "WHERE D.user_id = #{userId} " +
//            "ORDER BY D.created_at DESC")
//    List<Map<String, Object>> getDiaryEmojisForCalendar(@Param("userId") String userId);

    // 날짜별 상세 조회
    @Select("SELECT D.diary_id, D.user_id, D.diary_content, D.created_at, " +
            "E.emotion_emoji " +
            "FROM TEST_DIARY D " +
            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.diary_id " +
            "WHERE D.diary_id = #{diaryId}")
    DiaryVO getDiaryById(int diaryId);

    // 다이어리 등록
    @Insert("INSERT INTO TEST_DIARY (user_id, diary_content, created_at) " +
            "VALUES (#{user_id}, #{diary_content}, SYSDATE)")
    int addDiary(DiaryVO diaryVO);



    // 2차 수정 부분
//    // 다이어리 수정 (내용만)
//    @Update("UPDATE TEST_DIARY SET diary_content = #{diary_content} WHERE diary_id = #{diary_id}")
//    int updateDiary(DiaryVO diaryVO);
//
//    // 감정 이모지 수정 (다이어리 수정할 때 같이 호출 가능)
//    @Update("UPDATE TEST_EMOTION SET emotion_emoji = #{emotion_emoji} WHERE diary_id = #{diary_id}")
//    int updateEmotionEmoji(@Param("diary_id") int diaryId, @Param("emotion_emoji") String emotionEmoji);
//
//    // 다이어리 삭제 (감정은 ON DELETE CASCADE가 아니라면 별도 처리 필요)
//    @Delete("DELETE FROM TEST_DIARY WHERE diary_id = #{diaryId}")
//    int deleteDiary(int diaryId);
//
//    // 최근 생성된 diary_id 가져오기 (트리거 사용 중이면 옵션)
//    @Select("SELECT TEST_DIARY_SEQ.CURRVAL FROM dual")
//    int getCurrentDiaryId();

//    void updateEmotionScore(int diaryId, int score);
}
