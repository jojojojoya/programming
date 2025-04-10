package com.KHH.usermypage;

import java.io.IOException;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDataDAO.viewUserData(request);
        UserDataDAO.viewUserReviews(request);
        UserDataDAO.viewUserReservation(request);
        UserDataDAO.viewUserScrap(request);
        // 기본적으로 해당 유저의 계정 데이터 보여주기 (mypage view)
        request.getRequestDispatcher("jsp/UserMyPage/sj_index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}