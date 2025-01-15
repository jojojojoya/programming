package com.mz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test2")
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		
		out.print("</head>");
		out.print("<body>");

		for (int a = 1; a < 501; a++) {
			if (a % 10 == 0 ) {
				out.print("<h1 style =\"color : yellow\">  서블릿 실행 성공! ^^ " + a + "</h1>");
				
			} else	{
			out.print("<h1> 서블릿 실행 성공! ^^ " + a + "</h1>");
		}
			
			
			
		out.print("</body>");	
		out.print("</html>");}
	
	 // body에 style 자동완성 후 가져오는 방법도 있음
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
