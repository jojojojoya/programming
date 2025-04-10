package com.mz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Output")
public class Output extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("output으로 요청!");
		
	String s = request.getParameter("say");
	int c = Integer.parseInt(request.getParameter("cnt"));

	System.out.println(s);
	System.out.println(c);
	
	response.setCharacterEncoding("UTF-8");
	PrintWriter out = response.getWriter();
	out.print("<html>");
	out.print("<head>");
	out.print("<meta charset=\"UTF-8\">");
	
	out.print("</head>");
	out.print("<body>");
	

	for (int i = 0; i < c; i++) {
		out.print("<h1>" + s + "</h1>");
	}

	
	out.print("</body>");	
	out.print("</html>");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
