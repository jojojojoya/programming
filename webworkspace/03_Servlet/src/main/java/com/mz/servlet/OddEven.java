package com.mz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OddEven")
public class OddEven extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 값 받기 
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
//		String result = "홀";
//		if (num%2 ==0) {
//			result = "짝";
//		}
		
		String result = num % 2 ==0 ? "짝" : "홀";
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		
		out.print("</head>");
		out.print("<body>");
			out.print("<h1><marquee>" + result + "</marquee></h1>");
		out.print("</body>");	
		out.print("</html>");
		}
		


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
