package com.KHH.usermypage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserScrapPageC")
public class UserScrapPageC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int p = Integer.parseInt(req.getParameter("p"));
        UserDataDAO.viewUserScrap(req);
        UserDataDAO.scrapPaging(p, req, UserDataDAO.viewUserScrap(req));
        req.setAttribute("content", "/jsp/UserScrap/userScrapPage.jsp");
        req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
