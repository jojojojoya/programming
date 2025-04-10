package com.KHH.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginC")
public class LoginC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("jsp/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		LoginDAO.loginChk(request);
		if (request.getAttribute("result").equals("pw 에러!") || request.getAttribute("result").equals("존재하지 않는 회원")){
			response.sendRedirect("LoginC");
		} else{
			response.sendRedirect("HomeC");
		}
	}
}
