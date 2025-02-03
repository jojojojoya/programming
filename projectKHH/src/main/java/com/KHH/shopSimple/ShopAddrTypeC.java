package com.KHH.shopSimple;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShopAddrTypeC")
public class ShopAddrTypeC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String region = request.getParameter("data-region");
        System.out.println(region);
        ArrayList<ShopDetailDTO> shops = ShopDAO.getSdao().getShopsByRegion(region);

        Gson gson = new Gson();
        String json = gson.toJson(shops);
        System.out.println("JSON 변환 결과: " + json);
        response.getWriter().write(json);
    }
//        request.setCharacterEncoding("utf-8");
//        request.setAttribute("shopDetail", ShopDAO.getSdao().DetailShopInfo(request));
//        request.getRequestDispatcher("jsp/index_content/shopDetail.jsp").forward(request, response);


        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

        }
    }