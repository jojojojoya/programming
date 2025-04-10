package com.KHH.signUp;

import com.KHH.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDAO {
    private static final SignUpDAO SDAO = new SignUpDAO();

//    public static ArrayList<UserDTO> users = null;

    private SignUpDAO() {
    }

    public static SignUpDAO getSDAO() {
        return SDAO;
    }

    public void UserSignUp(HttpServletRequest request, HttpServletResponse response) {

//        String emailDomain = request.getParameter("email-domain");
//        String emailService = request.getParameter("email-service");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userNickname = request.getParameter("nickname");
        String userTel = request.getParameter("tel");
        String userGender = request.getParameter("gender");
        String userBirth = request.getParameter("birth");
        String temp_path = "default_profile.png";


//        int r_addrNumber = Integer.parseInt((request.getParameter("address")));
//        String userAddress = String.valueOf(r_addrNumber);
        String userPassword = request.getParameter("password");

        String sql = "INSERT INTO USER_ACCOUNT values (?,?,?,?,?,?,?,?,'level1',?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            pstmt.setString(2, userPassword);
            pstmt.setString(3, userName);
            pstmt.setString(4, userBirth);
            pstmt.setString(5, "도쿄");
            pstmt.setString(6, userTel);
            pstmt.setString(7, userGender);
            pstmt.setString(8, userNickname);
            pstmt.setString(9, temp_path);

            if (pstmt.executeUpdate() == 1) {
                System.out.println("User Sign Up Success!!!");
                request.setAttribute("content", "/jsp/signup/signUpComplete.jsp");
            } else {
                request.setAttribute("content", "/jsp/signup/signUpFail.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pstmt, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void OwnerSignUp(HttpServletRequest request, HttpServletResponse response) {
        String ownerName = request.getParameter("ownerName");
        String ownerEmail = request.getParameter("ownerEmail");
        String ownerNickname = request.getParameter("nickname");
        String ownerTel = request.getParameter("tel");
        String ownerBirth = request.getParameter("birth");
        String ownerPassword = request.getParameter("password");
        String temp_path = "default_profile.png";

        String sql = "INSERT INTO OWNER_ACCOUNT values (?,?,?,?,?,?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ownerEmail);
            pstmt.setString(2, ownerPassword);
            pstmt.setString(3, ownerName);
            pstmt.setString(4, ownerBirth);
            pstmt.setString(5, ownerTel);
            pstmt.setString(6, ownerNickname);
            pstmt.setString(7, null);
            pstmt.setString(8, temp_path);

            if (pstmt.executeUpdate() == 1) {
                System.out.println("Owner Sign Up Success!!!");
                request.setAttribute("content", "/jsp/signup/signUpComplete.jsp");
            } else {
                request.setAttribute("content", "/jsp/signup/signUpFail.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pstmt, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void CheckDuplicate(HttpServletRequest request, HttpServletResponse response) {
        String type = request.getParameter("type");
        String value = request.getParameter("value");

        ResultSet rs = null;

        // Database 연결
        try (Connection con = DBManager.connect()) {
            String query;
            if ("email".equals(type)) {
                query = "SELECT COUNT(*) FROM USER_ACCOUNT WHERE USER_EMAIL=?";
            } else if ("nickname".equals(type)) {
                query = "SELECT COUNT(*) FROM USER_ACCOUNT WHERE user_nickname=?";
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, value);
                rs = pstmt.executeQuery();
                rs.next();
                boolean exists = rs.getInt(1) > 0;
                response.getWriter().write("{\"exists\": " + exists + "}");
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
