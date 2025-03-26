package com.koyoi.main.service;

import com.koyoi.main.mapper.AdminMypageMapper;
import com.koyoi.main.vo.AdminMypageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class AdminMypageService {

    private final DataSource dataSource;

    @Autowired
    public AdminMypageService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private AdminMypageMapper adminMypageMapper;

    // 회원 목록 조회
    public List<AdminMypageVO> getAllUsers() {
        return adminMypageMapper.getAllUsers();
    }

    // 상담사 목록 조회
    public List<AdminMypageVO> getAllCounselors() {
        return adminMypageMapper.getAllCounselors();
    }

    // 상세 정보 조회
    public AdminMypageVO getUserById(String userId) {
        return adminMypageMapper.getUserById(userId);
    }
    
    // 삭제
    public int deleteUserById(String userId) {
        return adminMypageMapper.deleteUserById(userId);
    }

    // 수정
    public int updateUser(AdminMypageVO adminMypageVO) {
        return adminMypageMapper.updateUser(adminMypageVO);
    }

/*    public int getUserTotalCount() {
        return adminMypageMapper.selectUserTotalCount();
    }

    public List<AdminMypageVO> getPagedUserList(int offset, int size) {
        return adminMypageMapper.selectUserPage(offset, size);
    }*/
}
