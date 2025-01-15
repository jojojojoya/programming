package com.mz.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteMemberC")
public class DeleteMemberC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 삭제하는 일 - D - delete
		DAO_Member.deleteMember(request);
		
		DAO_Member.getAllMember(request);
		// 어디로?
		request.getRequestDispatcher("NewFile.jsp").forward(request, response);
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
