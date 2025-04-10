package com.KHH.community;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/CommunityPNDetailController")
public class CommunityPNDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommunityDAO.getCommunityPNDetail(req);
        CommunityDAO.getCommunityPNDDetailImage(req);
        req.setAttribute("content","/jsp/community/community_detail.jsp");
        req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}