package com.koyoi.main.service;

import com.koyoi.main.mapper.AnnouncementMapper;
import com.koyoi.main.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    public List<AnnouncementVO> getAllAnnouncements() {
        return announcementMapper.getAllAnnouncements();
    }
}
