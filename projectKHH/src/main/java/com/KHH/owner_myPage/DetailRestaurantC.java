package com.KHH.owner_myPage;

import java.io.IOException;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DetailRestaurantC")
public class DetailRestaurantC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant_DAO.DetailShow(request);
        request.setAttribute("content","/jsp/owner_myPage/DetailMyRestaurant.jsp");
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}