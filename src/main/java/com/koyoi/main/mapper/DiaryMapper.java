package com.koyoi.main.mapper;

import com.koyoi.main.vo.DiaryVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface DiaryMapper {

    // 캘린더 조회 (Emotion과 join)
    @Select("SELECT D.diary_id, " +
            "TO_CHAR(D.created_at, 'YYYY-MM-DD') AS DIARY_DATE, " +
            "E.emotion_emoji AS EMOTION_EMOJI " +
            "FROM TEST_DIARY D " +
            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.diary_id " +
            "WHERE D.user_id = #{userId} " +
            "ORDER BY D.created_at DESC")
    List<Map<String, Object>> getDiaryEmojisForCalendar(@Param("userId") String userId);

    // 일기 상세 조회 (diaryId 기준)
    @Select("SELECT D.diary_id, D.user_id, D.title, D.diary_content, " +
            "D.created_at, " +
            "E.emotion_emoji, E.emotion_score " +
            "FROM TEST_DIARY D " +
            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.diary_id " +
            "WHERE D.diary_id = #{diaryId}")
    DiaryVO getDiaryById(@Param("diaryId") int diaryId);

    // 일기 상세 조회 (날짜 기준)
    @Select("SELECT D.diary_id, D.user_id, D.title, D.diary_content, " +
            "D.created_at, " +
            "E.emotion_emoji, E.emotion_score " +
            "FROM TEST_DIARY D " +
            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.diary_id " +
            "WHERE D.user_id = #{userId} " +
            "AND TRUNC(D.created_at) = TO_DATE(#{date}, 'YYYY-MM-DD')")
    DiaryVO getDiaryByDate(@Param("userId") String userId, @Param("date") String date);

    // 주간조회
    @Select("SELECT D.diary_id, D.user_id, D.title, D.diary_content, " +
            "D.created_at, " +
            "E.emotion_emoji " +
            "FROM TEST_DIARY D " +
            "LEFT JOIN TEST_EMOTION E ON D.diary_id = E.diary_id " +
            "WHERE D.user_id = #{userId} " +
            "AND D.created_at >= TO_DATE(#{startDate}, 'YYYY-MM-DD') " +
            "AND D.created_at < TO_DATE(#{endDate}, 'YYYY-MM-DD') " +
            "ORDER BY D.created_at")
    List<DiaryVO> getWeeklyDiaries(@Param("userId") String userId,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate);


    // 일기 등록
    @Insert("INSERT INTO TEST_DIARY (user_id, title, diary_content, created_at) " +
            "VALUES (#{user_id}, #{title}, #{diary_content}, #{created_at})")
    int addDiary(DiaryVO diaryVO);

    // 일기 등록 후 diary_id 가져오기
    @Select("SELECT diary_id " +
            "FROM TEST_DIARY " +
            "WHERE user_id = #{user_id} " +
            "AND title = #{title} " +
            "AND TRUNC(created_at) = TRUNC(#{created_at}) " +
            "ORDER BY diary_id DESC FETCH FIRST 1 ROWS ONLY")
    Integer findDiaryId(@Param("user_id") String userId,
                        @Param("title") String title,
                        @Param("created_at") LocalDateTime createdAt);

    // 일기 수정
    @Update("UPDATE TEST_DIARY " +
            "SET title = #{title}, " +
            "    diary_content = #{diary_content} " +
            "WHERE diary_id = #{diary_id} " +
            "AND user_id = #{user_id}")
    int updateDiary(DiaryVO diaryVO);

    // 일기 삭제
    @Delete("DELETE FROM TEST_DIARY " +
            "WHERE diary_id = #{diaryId} " +
            "AND user_id = #{userId}")
    int deleteDiary(@Param("diaryId") int diaryId, @Param("userId") String userId);
}