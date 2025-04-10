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

    /* 공지사항 전체 목록 가져오기 (사용X) */
    public List<AnnouncementVO> getAllAnnouncements() {
        List<AnnouncementVO> announcements = announcementMapper.getAllAnnouncements();
        return announcements;
    }

    /* 5개의 최신 공지사항 가져오기 */
    public List<AnnouncementVO> getFiveAnnouncements() {
        List<AnnouncementVO> announcements = announcementMapper.getFiveAnnouncements();
        LocalDateTime threeDaysAgo = LocalDateTime.now().minus(3, ChronoUnit.DAYS); // 현재 시각 - 3일

        // 3일 이내에 생성된 공지사항에 "New" 표시
        for (AnnouncementVO announcement : announcements) {
            if (announcement.getCreated_at().isAfter(threeDaysAgo)) {
                announcement.setIsNew("Y");  // 3일 이내면 "New" 표시(VO에 "Y" 저장)
            } else {
                announcement.setIsNew("N");  // 오래된 공지는 "New" 표시 없음(VO에 "N" 저장)
            }
        }
        return announcements;
    }

    /* 공지사항 상세 조회 */
    public AnnouncementVO getAnnouncementById(int id) {
        return announcementMapper.getAnnouncementById(id);
    }

    /* 공지사항 수정 */
    public int updateAnnouncement(AnnouncementVO announcementVO) {
        return announcementMapper.updateAnnouncement(announcementVO);
    }

    /* 공지사항 삭제 */
    public int deleteAnnouncement(int announcementId) {
        return announcementMapper.deleteAnnouncement(announcementId);
    }

    /* 공지사항 작성 */
    public int createAnnouncement(AnnouncementVO announcementVO) {
        return announcementMapper.createAnnouncement(announcementVO);
    }


    /* 공지사항 페이징 */
    public List<AnnouncementVO> getPagedAnnouncementList(int offset, int size) {
        return announcementMapper.selectAnnouncementPage(offset, size);
    }

    public int getTotalCount() {
        return announcementMapper.selectAnnouncementTotalCount();
    }


}
