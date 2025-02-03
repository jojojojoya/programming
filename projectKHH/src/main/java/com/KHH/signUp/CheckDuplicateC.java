package com.KHH.signUp;

import com.KHH.main.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/CheckDuplicateC")
public class CheckDuplicateC extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type"); // email or nickname
        String value = request.getParameter("value");

        try (Connection con = DBManager.connect()) {
            String query;
            if ("email".equals(type)) {
                query = "SELECT COUNT(*) FROM USER_ACCOUNT WHERE user_email = ?";
            } else if ("nickname".equals(type)) {
                query = "SELECT COUNT(*) FROM USER_ACCOUNT WHERE user_nickname = ?";
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, value);
                try (ResultSet rs = pstmt.executeQuery()) {
                    rs.next();
                    boolean exists = rs.getInt(1) > 0;

                    // JSON 형태로 응답
                    response.setContentType("application/json");
                    response.getWriter().write("{\"exists\": " + exists + "}");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

