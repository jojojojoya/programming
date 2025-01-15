package com.mz.login.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginC")
public class LoginC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃일
		LoginDAO.logout(request);
		
		// 어디로?
		request.setAttribute("content", "home.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 일
		LoginDAO.login(request);
		//어디로??
		LoginDAO.loginCheck(request);
		request.setAttribute("content", "home.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}