package com.koyoi.main.mapper;

import com.koyoi.main.vo.AnnouncementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

/*    @Select("SELECT A.announcement_id, A.admin_id, U.user_id, A.title, A.content, A.created_at " +
            "FROM TEST_ANNOUNCEMENT A LEFT JOIN TEST_USER U ON A.admin_id = U.user_id ORDER BY A.created_at DESC) WHERE ROWNUM <= 5")
    List<AnnouncementVO> getAllAnnouncements();*/

    @Select("SELECT * FROM ( " +
            "SELECT A.announcement_id, A.admin_id, U.user_id, A.title, A.content, A.created_at " +
            "FROM TEST_ANNOUNCEMENT A " +
            "LEFT JOIN TEST_USER U ON A.admin_id = U.user_id " +
            "ORDER BY A.created_at DESC " +
            ") WHERE ROWNUM <= 5")
    List<AnnouncementVO> getAllAnnouncements();

    @Select("SELECT * FROM TEST_ANNOUNCEMENT WHERE announcement_id = #{id}")
    AnnouncementVO getAnnouncementById(int id);
}
