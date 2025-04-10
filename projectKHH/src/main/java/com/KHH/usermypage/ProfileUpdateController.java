package com.KHH.usermypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProfileUpdateController")
public class ProfileUpdateController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDataDAO.viewUserData(request);
        UserDataDAO.viewUserReviews(request);
        UserDataDAO.viewUserReservation(request);
        UserDataDAO.viewUserScrap(request);

        request.setAttribute("content", "/jsp/UserMyPage/userMyPage.jsp");
        request.setAttribute("profile","/jsp/UserMyPage/updateUserMyPage.jsp");
        request.getRequestDispatcher("jsp/main.jsp").forward(request,response);


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 프로필 업데이트
        UserDataDAO.userProfileUpdate(request);

        // 마이페이지로 리다이렉트
        response.sendRedirect("UserC");


    }
    }