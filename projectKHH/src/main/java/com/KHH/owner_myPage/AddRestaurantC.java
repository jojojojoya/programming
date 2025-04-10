package com.KHH.owner_myPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddRestaurantC")
public class AddRestaurantC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("content","/jsp/owner_myPage/AddRestaurant.jsp");
        request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("컨트롤러로 넘어옴");
        Restaurant_DAO.GetShopPK();
//        String ownerName = Restaurant_DAO.GetOwnerName(request);
        Restaurant_DAO.InsertRestaurant(request);
        response.sendRedirect("ShowOwnerMyPageController");

    }
}