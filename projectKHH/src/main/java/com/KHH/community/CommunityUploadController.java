package com.KHH.community;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CommunityUploadController")
public class CommunityUploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("content","/jsp/community/community_upload.jsp");
        req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommunityDAO.getCommunityPK();
        try {
            CommunityDAO.insertCommunityAndImages(req);
            resp.sendRedirect("/CommunityListController");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}