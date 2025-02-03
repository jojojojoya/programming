package com.KHH.signUp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/CheckC")
public class CheckC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String g = req.getParameter("gender");
        if(g!=null){
            req.setAttribute("content","/jsp/signup/user_signUp_check.jsp");
            req.getRequestDispatcher("jsp/main.jsp").forward(req,resp);

        }
        else{
            req.setAttribute("content","/jsp/signup/owner_signUp_check.jsp");
            req.getRequestDispatcher("jsp/main.jsp").forward(req,resp);
        }
    }
}