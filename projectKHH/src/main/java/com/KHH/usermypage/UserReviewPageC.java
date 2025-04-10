package com.KHH.usermypage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserReviewPageC")
public class UserReviewPageC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int p = Integer.parseInt(req.getParameter("p"));
        UserDataDAO.viewUserReviews(req);
        UserDataDAO.reviewsPaging(p, req, UserDataDAO.viewUserReviews(req));
        req.setAttribute("content", "/jsp/UserReviews/userReviewsPage.jsp");
        req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
