package com.KHH.owner_myPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Edit_profileC")
public class Edit_profileC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant_DAO.ShowProfile(request);
        request.setAttribute("content","/jsp/owner_myPage/UpdateProfile.jsp");
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Restaurant_DAO.EditProfile(request);
        response.sendRedirect("/ShowOwnerMyPageController");
    }
}