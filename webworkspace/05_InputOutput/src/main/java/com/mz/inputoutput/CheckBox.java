package com.mz.inputoutput;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckBox")
public class CheckBox extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 1. 값 받기 
		String[] c = request.getParameterValues("chk");
			
		String xx = request.getParameter("x");
		String y = request.getParameter("y");
		System.out.println(xx);
		System.out.println(y);
	
	
		
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset=\"UTF-8\">");
			
			out.print("</head>");
			out.print("<body>");


				for (String s : c) {
					out.printf("<h1>%s</h1>",s);
				}
				
				
				
			out.print("</body>");	
			out.print("</html>");
		
		
		}
	
 
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
