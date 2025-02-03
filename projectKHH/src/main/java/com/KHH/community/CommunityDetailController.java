package com.KHH.community;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/CommunityDetailController")
public class CommunityDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommunityDAO.updateCommunityReadCnt(req);
        CommunityDAO.getCommunityDetail(req);
        CommunityDAO.getCommunityDetailImage(req);
        CommunityDAO.getCommunityComment(req);
        req.setAttribute("content", "/jsp/community/community_detail.jsp");
        req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("컨트롤러넘어옴");
    }
}