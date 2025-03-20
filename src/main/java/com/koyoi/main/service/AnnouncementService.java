package com.koyoi.main.service;

import com.koyoi.main.mapper.AnnouncementMapper;
import com.koyoi.main.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AnnouncementService {

    private final DataSource dataSource;

    @Autowired
    public AnnouncementService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private AnnouncementMapper announcementMapper;

    public List<AnnouncementVO> getAllAnnouncements() {
        List<AnnouncementVO> announcements = announcementMapper.getAllAnnouncements();
        LocalDateTime threeDaysAgo = LocalDateTime.now().minus(3, ChronoUnit.DAYS);

        // 3일 이내에 생성된 공지사항에 "New" 표시
        for (AnnouncementVO announcement : announcements) {
            if (announcement.getCreated_at().isAfter(threeDaysAgo)) {
                announcement.setIsNew("Y");  // 3일 이내면 "New" 표시
            } else {
                announcement.setIsNew("N");  // 오래된 공지는 "New" 표시 없음
            }
        }
        return announcements;
    }
}
