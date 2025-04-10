package com.mz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i = request.getParameter("id");
		String p = request.getParameter("pw");
		System.out.println(i);
		System.out.println(p);
	
	
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		
		out.print("</head>");
		out.print("<body>");

		out.print("<h1 style =\"color : red\">"  + i + "</h1>");
		out.print("<h1 style =\"color : red\">" + p + "</h1>");
		
		if (i.equals("sj") && p.equals("1004")) {
			out.print("<h1 style =\"color : red\">  로그인 성공! </h1>");
		} else if (!i.equals("sj")) {
			out.print("<h1 style =\"color : red\">  존재하지 않는 회원입니다 </h1>");
		} else {
			out.print("<h1 style =\"color : red\">  비번 오류 </h1>");
			
		}
			
			
		out.print("</body>");	
		out.print("</html>");
	}
	

	
	
	
	//1. 값 입력받기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
