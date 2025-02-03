package com.KHH.community;

import com.KHH.main.DBManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import java.util.List;

public class CommunityDAO {

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;   // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


    public static void countCommunityData(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "select count(*) from community_info";

        try {
            con = DBManager.connect();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                request.setAttribute("count", rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pst, rs);
        }
    }

    public static void selectAllList(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "select * from community_info order by community_no DESC";

        CommunityDTO com = null;
        ArrayList<CommunityDTO> coms = new ArrayList<CommunityDTO>();

        try {
            con = DBManager.connect();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                com = new CommunityDTO();
                com.setNo(rs.getInt(1));
                com.setTitle(rs.getString(2));
                com.setContent(rs.getString(3));
                com.setNickname(rs.getString(4));
                com.setDate(rs.getDate(5));
                com.setReadcnt(rs.getInt(6));
                coms.add(com);
            }
            request.setAttribute("coms", coms);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pst, rs);
        }
    }

    public static void updateCommunityReadCnt(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String no = request.getParameter("no");

        String sql = "update community_info set community_readcnt = community_readcnt + 1 where community_no = ?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            if (pstmt.executeUpdate() == 1) {
                System.out.println("조회수 업데이트 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, null);
        }
    }

    public static void getCommunityDetail(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String no = request.getParameter("no");

        String sql = "select * from community_info where community_no = ?";

        CommunityDTO com = null;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                com = new CommunityDTO();
                com.setNo(rs.getInt(1));
                com.setTitle(rs.getString(2));
                com.setContent(rs.getString(3));
                com.setNickname(rs.getString(4));
                com.setDate(rs.getDate(5));
                com.setReadcnt(rs.getInt(6));
            }
            request.setAttribute("com", com);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getCommunityDetailImage(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String no = req.getParameter("no");

        String sql = "select * from community_image where community_no = ?";

        CommunityImageDTO image = null;
        ArrayList<CommunityImageDTO> images = new ArrayList<>();

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                image = new CommunityImageDTO();
                image.setNo(rs.getInt(1));
                image.setImage(rs.getString(2));
                images.add(image);
            }
            req.setAttribute("images", images);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getCommunityPNDetail(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";

        String no = req.getParameter("no");
        String page = req.getParameter("page");

        if (page.equals("prev")) {
            sql = "SELECT * FROM community_info WHERE community_no > ? ORDER BY community_no DESC";
        } else if (page.equals("next")) {
            sql = "SELECT * FROM community_info WHERE community_no < ? ORDER BY community_no ASC";
        }

        CommunityDTO com = null;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                com = new CommunityDTO();
                com.setNo(rs.getInt(1));
                com.setTitle(rs.getString(2));
                com.setContent(rs.getString(3));
                com.setNickname(rs.getString(4));
                com.setDate(rs.getDate(5));
                com.setReadcnt(rs.getInt(6));
            }
            req.setAttribute("com", com);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    public static void getCommunityPNDDetailImage(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String no = req.getParameter("no");
        String page = req.getParameter("page");

        String sql = "";

        if (page.equals("prev")) {
            sql = "SELECT * FROM community_image WHERE community_no > ? ORDER BY community_no DESC";
        } else if (page.equals("next")) {
            sql = "SELECT * FROM community_image WHERE community_no < ? ORDER BY community_no ASC";
        }

        CommunityImageDTO image = null;
        ArrayList<CommunityImageDTO> images = new ArrayList<>();

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                image = new CommunityImageDTO();
                image.setNo(rs.getInt(1));
                image.setImage(rs.getString(2));
                images.add(image);
            }
            req.setAttribute("images", images);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getCommunitySearchItems(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String item = req.getParameter("item");
        String value = req.getParameter("value");

        String sql = "";
        if (item.equals("title")) {
            sql = "select * from community_info where community_title like ? order by community_no DESC";
        } else if (item.equals("writer")) {
            sql = "select * from community_info where community_nickname like ? order by community_no DESC";
        }

        CommunityDTO com = null;
        ArrayList<CommunityDTO> coms = new ArrayList<CommunityDTO>();

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + value + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                com = new CommunityDTO();
                com.setNo(rs.getInt(1));
                com.setTitle(rs.getString(2));
                com.setContent(rs.getString(3));
                com.setNickname(rs.getString(4));
                com.setDate(rs.getDate(5));
                com.setReadcnt(rs.getInt(6));
                coms.add(com);
            }
            req.setAttribute("coms", coms);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    public static void countSearchCommunityData(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String item = req.getParameter("item");
        String value = req.getParameter("value");

        String sql = "";
        if (item.equals("title")) {
            sql = "select count(*) from community_info where community_title like ?";
        } else if (item.equals("writer")) {
            sql = "select count(*) from community_info where community_nickname like ?";
        }

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + value + "%");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                req.setAttribute("count", rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    public static int getCommunityPK() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select max(community_no) from community_info";

        int no = 0;
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        System.out.println("pk최댓값: " + no);
        return no + 1;
    }

    public static void insertCommunityAndImages(HttpServletRequest req) {
        String path = req.getServletContext().getRealPath("image/community");
        System.out.println(path);
        File directory = new File(path);

        //디렉토리가 없으면 생성
        if (!directory.exists() && !directory.mkdirs()) {

        }

        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        String sql1 = "INSERT INTO community_info VALUES (?, ?, ?, ?, SYSDATE, 0)";
        String sql2 = "INSERT INTO community_image VALUES (?, ?)";

        try {
            // 파일 업로드를 위한 설정
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            // Set maximum request size (including file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);
            // 요청에서 파일 아이템 추출
            List<FileItem> items = upload.parseRequest(req);

            String writer = null, title = null, content = null;
            int no = getCommunityPK(); // 새로운 PK 생성

            con = DBManager.connect();

            Map<String, String> fieldMap = new HashMap<>();

            for (FileItem item : items) {
                if (item.isFormField()) { // 폼 필드인지 확인
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("UTF-8"); // UTF-8로 문자열 인코딩

                    // 특정 fieldName에 따라 Map에 저장
                    fieldMap.put(fieldName, fieldValue);
                }
            }

            // 필요한 값 추출 (for문 외부에서 사용 가능)
            title = fieldMap.getOrDefault("title", null);
            content = fieldMap.getOrDefault("content", null);
            writer = fieldMap.getOrDefault("writer", null);

            // community_info 테이블 저장
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setInt(1, no);
            pstmt1.setString(2, title);
            pstmt1.setString(3, content.replace("\r\n", "<br>"));
            pstmt1.setString(4, writer);
            if (pstmt1.executeUpdate() == 1) {
                System.out.println("community_info insert success");

            }

            // 각 요청 파라미터 및 파일 처리
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // 일반 폼 필드
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("UTF-8");

                    switch (fieldName) {
                        case "writer":
                            writer = fieldValue;
                            break;
                        case "title":
                            title = fieldValue;
                            break;
                        case "content":
                            content = fieldValue;
                            break;
                    }
                } else {
                    // 파일 업로드 처리
                    String fileName = new File(item.getName()).getName();
                    int dotIndex = fileName.lastIndexOf('.');
                    String fileExtension = fileName.substring(dotIndex);
                    String uniqueFileName = UUID.randomUUID().toString().split("-")[0] + fileExtension;
                    File uploadedFile = new File(directory, uniqueFileName);
                    item.write(uploadedFile); // 파일 저장

                    // DB에 파일 이름 저장
                    pstmt2 = con.prepareStatement(sql2);
                    pstmt2.setInt(1, no);
                    pstmt2.setString(2, uniqueFileName);
                    if (pstmt2.executeUpdate() == 1) {
                        System.out.println("community_image insert success: " + fileName);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt1, null);
            DBManager.close(null, pstmt2, null);
        }
    }


    public static void insertCommunityComment(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String no = req.getParameter("no");
        String nickname = req.getParameter("nickname");
        String comment_content = req.getParameter("comment-content");

        String sql = "insert into comment_info values(comment_parentno_seq.nextval,1,?,?,?,sysdate)";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,no);
            pstmt.setString(2, nickname);
            pstmt.setString(3,comment_content);
            if(pstmt.executeUpdate() == 1){
                System.out.println("등록성공");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBManager.close(con, pstmt, null);
        }
    }


    public static void getCommunityComment(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String no = req.getParameter("no");

        String sql = "select * from comment_info where community_no = ? order by comment_parentno desc";

        CommentDTO comment = null;
        ArrayList<CommentDTO> comments = new ArrayList<>();

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            while(rs.next()){
                comment = new CommentDTO();
                comment.setParentNo(rs.getInt(1));
                comment.setChildNo(rs.getInt(2));
                comment.setCommunityNo(rs.getInt(3));
                comment.setContent(rs.getString(4));
                comment.setNickname(rs.getString(5));
                comment.setDate(rs.getDate(6));
                comments.add(comment);
            }
            req.setAttribute("comments", comments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {

        }
    }
}
