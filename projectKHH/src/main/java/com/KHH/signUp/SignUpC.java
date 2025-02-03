package com.KHH.signUp;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpC")
public class SignUpC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("content","/jsp/signup/choose_userType.jsp");
        request.getRequestDispatcher("jsp/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        if (Objects.equals(request.getParameter("userType"), "user")) {
            SignUpDAO.getSDAO().UserSignUp(request, response);
        } else if (Objects.equals(request.getParameter("userType"), "owner")) {
            SignUpDAO.getSDAO().OwnerSignUp(request, response);
        }

        request.getRequestDispatcher("jsp/main.jsp").forward(request, response);

//        request.getRequestDispatcher("jsp/signup/signUpComplete.jsp").forward(request, response);
    }
}