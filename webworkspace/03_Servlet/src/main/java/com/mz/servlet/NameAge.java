package com.mz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NameAge")
public class NameAge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	1. 값 받기
		String n = request.getParameter("name");
		int a = Integer.parseInt(request.getParameter("age"));
		System.out.println(n);
		System.out.println(a);
//	2. 나이가 20넘으면 00님 안녕하세요
//		안념으면 00아 안녕
	
			String  result  = a >20 ? "님 안녕하세요" : "아 안녕";
			
		
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		
		out.print("</head>");
		out.print("<body>");
			out.print("<h1>" + n + result + "</h1>");
		out.print("</body>");	
		out.print("</html>");
		}
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
