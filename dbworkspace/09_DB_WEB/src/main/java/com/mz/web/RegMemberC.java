package com.mz.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegMemberC")
public class RegMemberC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 무슨 일? = 등록하는 일 - C - insert
		DAO_Member.regMember(request);
		
		// members를 만드는 일
		DAO_Member.getAllMember(request);
		
		// 어디로?
		request.getRequestDispatcher("NewFile.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
