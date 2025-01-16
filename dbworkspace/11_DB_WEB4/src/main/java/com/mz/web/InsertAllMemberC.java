package com.mz.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/InsertAllMemberC")
public class InsertAllMemberC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 전체 조회하는 일
		MemberDao.insertAllMember(M.getMember(request));

		response.sendRedirect("SelectAllMemberC");
			// 어디로?
//		request.setAttribute("members", MemberDao.selectAllMember()); 
//			request.getRequestDispatcher("output.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
