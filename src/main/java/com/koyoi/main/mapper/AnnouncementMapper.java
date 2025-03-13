package com.koyoi.main.mapper;

import com.koyoi.main.vo.AnnouncementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    @Select("select * from test_announcement ORDER BY created_at DESC")
    List<AnnouncementVO> getAllAnnouncements();
}
