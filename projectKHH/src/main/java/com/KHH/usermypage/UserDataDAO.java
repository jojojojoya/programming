package com.KHH.usermypage;


import com.KHH.userreservationpage.MyPageReservationDTO;
import com.KHH.userreservationpage.ReservationDTO;
import com.KHH.userreviewspage.ReviewsDTO;
import com.KHH.userscrappage.ScrapDTO;
import com.KHH.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDataDAO {



    public static void viewUserData (HttpServletRequest request) {
    // 그냥 기본적으로 해당 유저의 계정 정보 보여주기 >> view data
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
//        String userEmail = request.getParameter("user_email");
//                request.getSession().getAttribute()
        String sql = "select * from user_account where user_email=?";

       try {
           con = DBManager.connect();
           pstmt = con.prepareStatement(sql);
           pstmt.setString(1, "user2@example.com"); // 이메일 수정필요
           rs = pstmt.executeQuery();

            if (rs.next()) {
                UserDataDTO user = new UserDataDTO();
                user.setUser_email(rs.getString("user_email"));
                user.setUser_nickname(rs.getString("user_nickname"));
                user.setUser_grade(rs.getString("user_grade"));
                user.setUser_picture(rs.getString("user_picture"));
                request.getSession().setAttribute("user", user);
                System.out.println("연결성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }


//유저 리뷰들 보여주기
public static ArrayList<ReviewsDTO> viewUserReviews(HttpServletRequest request) {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "SELECT r.review_shop, r.review_content, r.review_date, r.review_nickname, s.shop_name" +
            " FROM review_info r, shop_info s" +
            " WHERE r.REVIEW_SHOP = s.SHOP_NO and r.review_nickname = ?";

    UserDataDTO user = (UserDataDTO) request.getSession().getAttribute("user");
    ArrayList<ReviewsDTO> reviews = new ArrayList<>();
    try {
        con = DBManager.connect();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUser_nickname());
        rs = pstmt.executeQuery();

        while (rs.next()) {
            ReviewsDTO review = new ReviewsDTO();
            review.setReview_shop(rs.getInt(1));
            review.setReview_content(rs.getString(2));
            review.setReview_date(rs.getDate(3));
            review.setReview_nickname(rs.getString(4));
            review.setShop_name(rs.getString(5));
            reviews.add(review);
            System.out.println(review); // 리뷰 확인
        }

        request.setAttribute("reviews", reviews);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBManager.close(con, pstmt, rs);
    }
        return reviews;
}

    //예약 확인
    public static ArrayList<MyPageReservationDTO> viewUserReservation(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
//        String user_email = request.getParameter("user_email");
        String sql = "SELECT r.reservation_email, r.reservation_date, r.reservation_people, r.reservation_name, r.reservation_tel, s.shop_name, s.shop_content, s.shop_tel, s.shop_addr, i.shop_image FROM reservation_info r JOIN SHOP_INFO s ON r.reservation_shop = s.shop_no JOIN shop_image i ON s.shop_no = i.shop_no WHERE r.reservation_email = ?";
        UserDataDTO user = (UserDataDTO) request.getSession().getAttribute("user");
            ArrayList<MyPageReservationDTO> myreservations = new ArrayList<>();

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUser_email());
            //나중에 이메일부분 real DB 변경시 파라미터값 대체 필요
            rs = pstmt.executeQuery();

            while(rs.next())  {
                MyPageReservationDTO myreservation = new MyPageReservationDTO();
                myreservation.setReservation_email(rs.getString("reservation_email"));
                myreservation.setReservation_date(rs.getString("reservation_date"));
                myreservation.setReservation_people(rs.getInt("reservation_people"));
                myreservation.setReservation_name(rs.getString("reservation_name"));
                myreservation.setReservation_tel(rs.getString("reservation_tel"));
                myreservation.setShop_name(rs.getString("shop_name"));
                myreservation.setShop_tel(rs.getString("shop_tel"));
                myreservation.setShop_addr(rs.getString("shop_addr"));
                myreservation.setShop_image(rs.getString("shop_image"));
                myreservations.add(myreservation);

                System.out.println(myreservation); //예약 확인
            }
            request.setAttribute("myreservations", myreservations);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return myreservations;
    }

    // 스크랩 확인
    public static ArrayList<ScrapDTO> viewUserScrap(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String user_email = request.getParameter("user_email");
        String sql =   "SELECT distinct si.shop_no, si.shop_name, ss.scrap_date, sim.shop_image, ss.scrap_email, si.shop_addr, si.shop_tel, si.shop_content" +
                " FROM scrap_shop ss, shop_info si, shop_image sim" +
                " WHERE" +
                "    ss.SCRAP_SHOP = si.SHOP_NO and" +
                "    sim.SHOP_NO = si.SHOP_NO and" +
                "    ss.scrap_email = ?";
        UserDataDTO user = (UserDataDTO) request.getSession().getAttribute("user");
        ArrayList<ScrapDTO> scraps = new ArrayList<>();
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUser_email());
            rs = pstmt.executeQuery();


            while(rs.next())  {
                ScrapDTO scrap = new ScrapDTO();
                scrap.setScrap_no(rs.getInt("shop_no"));
                scrap.setScrap_email(rs.getString("scrap_email"));
                scrap.setShop_image(rs.getString("shop_image"));
                scrap.setScrap_date(rs.getString("scrap_date"));
                scrap.setShop_name(rs.getString("shop_name"));
                scrap.setShop_addr(rs.getString("shop_addr"));
                scrap.setShop_tel(rs.getString("shop_tel"));
                scrap.setShop_content(rs.getString("shop_content"));
                scraps.add(scrap);
                System.out.println(scrap);

            }
            request.setAttribute("scraps", scraps);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return scraps;
    }


    // profile Update 메소드
    public static void userProfileUpdate(HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("image");  Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // MultipartRequest 처리
            MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 20, "utf-8", new DefaultFileRenamePolicy());
            String userNickname = mr.getParameter("user_nickname");


            // 파일 처리
            String newImg = mr.getFilesystemName("newImg");
            UserDataDTO user = (UserDataDTO) request.getSession().getAttribute("user");

            System.out.println(userNickname);
            System.out.println(newImg);
            System.out.println(user.getUser_email());
            // 새 이미지가 없으면 기존 이미지 사용
            String updatedPicture = (newImg != null) ? newImg : user.getUser_picture();

            // DB 업데이트
            con = DBManager.connect();
            String sql = "UPDATE user_account SET user_nickname = ?, user_picture = ? WHERE user_email = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userNickname);
            pstmt.setString(2, updatedPicture);
            pstmt.setString(3, user.getUser_email());
            if (pstmt.executeUpdate() == 1){
                System.out.println("----------수정------------");
            }

            // 기존 이미지 파일 삭제
            if (newImg != null && user.getUser_picture() != null && !user.getUser_picture().equals(newImg)) {
                File oldFile = new File(path + "/" + user.getUser_picture());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            // 세션 갱신 (변경된 값 반영)
            request.getSession().setAttribute("user_nickname", userNickname);
            request.getSession().setAttribute("user_picture", updatedPicture);

            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, null);
        }
    }
//
//
//    public static void updateSessionUser(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String userEmail = (String) session.getAttribute("user_email");
//
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBManager.connect();
//            String sql = "SELECT user_nickname, user_picture FROM user_account WHERE user_email = ?";
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, userEmail);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                session.setAttribute("user_nickname", rs.getString("user_nickname"));
//                session.setAttribute("user_picture", rs.getString("user_picture"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.close(con, pstmt, rs);
//        }
//    }

    // 리뷰 페이징 기능
    public static void reviewsPaging(int pageNum, HttpServletRequest request, ArrayList<ReviewsDTO> reviews) {
        request.setAttribute("curPageNum", pageNum);

        int total = reviews.size(); // 총 데이터 수
        int cnt = 3; // 한 페이지당 보여줄 개수

        // 페이지 수 == 마지막 페이지 번호
        int pageCount = (int) Math.ceil((double) total / cnt); // 총 페이지 수
        request.setAttribute("pageCount", pageCount);

        // 시작, 끝
        int start = total - (cnt * (pageNum - 1));
        int end = (pageNum == pageCount) ? -1 : start - cnt;
        ArrayList<ReviewsDTO> items = new ArrayList<>();
        for (int i = start - 1; i > end; i--) {
            items.add(reviews.get(i));
        }

        request.setAttribute("reviews", items); // 현재 페이지 데이터로 설정
    }



    //예약가게 페이징 기능
    public static void  reservationPaging(int pageNum, HttpServletRequest request, ArrayList<MyPageReservationDTO> myreservations) {
        request.setAttribute("curPageNum", pageNum);

        int total = myreservations.size(); //총데이터수
        int cnt = 3; //한페이지당보여줄개수

        // 페이지 수 == 마지막 페이지 번호
        int pageCount =(int) Math.ceil((double)total/ cnt); //총페이지수
        //System.out.println(pageCount); //페이지 개수(총페이지수)
        request.setAttribute("pageCount", pageCount);

        //int pageNum = 1; //페이지번호

        //시작,끝
        int start = total - (cnt * (pageNum - 1));
        int end = (pageNum == pageCount) ? -1 : start - (cnt + 1);

        ArrayList<MyPageReservationDTO> items= new ArrayList<>();
        for (int i = start - 1; i > end; i--) {
            items.add(myreservations.get(i));
        }
        request.setAttribute("myreservations", items);
    }


    //예약가게 페이징 기능
    public static void  scrapPaging(int pageNum, HttpServletRequest request, ArrayList<ScrapDTO> scraps) {
        request.setAttribute("curPageNum", pageNum);

        int total = scraps.size(); //총데이터수
        int cnt = 3; //한페이지당보여줄개수

        // 페이지 수 == 마지막 페이지 번호
        int pageCount =(int) Math.ceil((double)total/ cnt); //총페이지수
        //System.out.println(pageCount); //페이지 개수(총페이지수)
        request.setAttribute("pageCount", pageCount);

        //int pageNum = 1; //페이지번호

        //시작,끝
        int start = total - (cnt * (pageNum - 1));
        int end = (pageNum == pageCount) ? -1 : start - (cnt + 1);


        ArrayList<ScrapDTO> items= new ArrayList<>();

        for (int i = start - 1; i > end; i--) {
            items.add(scraps.get(i));
        }
        // For each 로 뿌리기
        // 페이징된 리스트를 request에 저장
        request.setAttribute("scraps", items);
    }}


