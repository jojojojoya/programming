package com.koyoi.main.mapper;

import com.koyoi.main.vo.AnnouncementVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    /* 모든 공지사항 가져오기 */
    @Select("SELECT A.announcement_id, A.admin_id, U.user_id, A.title, A.content, A.created_at " +
            "FROM TEST_ANNOUNCEMENT A " +
            "LEFT JOIN TEST_USER U ON A.admin_id = U.user_id " +
            "ORDER BY A.created_at DESC")
    List<AnnouncementVO> getAllAnnouncements();

    /* 5개의 최신 공지사항 가져오기 */
    @Select("SELECT * FROM ( " +
            "SELECT A.announcement_id, A.admin_id, U.user_id, A.title, A.content, A.created_at " +
            "FROM TEST_ANNOUNCEMENT A " +
            "LEFT JOIN TEST_USER U ON A.admin_id = U.user_id " +
            "ORDER BY A.created_at DESC " +
            ") WHERE ROWNUM <= 5")
    List<AnnouncementVO> getFiveAnnouncements();

    /* 공지사항 상세 */
    @Select("SELECT * FROM TEST_ANNOUNCEMENT WHERE announcement_id = #{id}")
    AnnouncementVO getAnnouncementById(int id);

    /* 공지사항 수정 */
    @Update("UPDATE TEST_ANNOUNCEMENT SET title = #{title}, content = #{content} WHERE announcement_id = #{announcement_id}")
    int updateAnnouncement(AnnouncementVO announcementVO);

    /* 공지사항 삭제 */
    @Delete("DELETE FROM TEST_ANNOUNCEMENT WHERE announcement_id = #{announcementId}")
    int deleteAnnouncement(int announcementId);

    /* 공지사항 작성 */
    @Insert("INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)" +
            "VALUES (#{admin_id}, #{title}, #{content}, SYSDATE)")
    int createAnnouncement(AnnouncementVO announcementVO);

    
    /* 공지사항 페이징 */
    @Select("SELECT * FROM TEST_ANNOUNCEMENT ORDER BY created_at DESC OFFSET #{offset} ROWS FETCH NEXT #{size} ROWS ONLY")
    List<AnnouncementVO> selectAnnouncementPage(int offset, int size);

    @Select("SELECT COUNT(*) FROM TEST_ANNOUNCEMENT")
    int selectAnnouncementTotalCount();
}
