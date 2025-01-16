package com.mz.inputoutput;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Calc")
public class Calc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int xx = Integer.parseInt(request.getParameter("x"));
	int yy = Integer.parseInt(request.getParameter("y"));
	System.out.println(xx);
	System.out.println(yy);
	
	
	response.setCharacterEncoding("UTF-8");
	PrintWriter out = response.getWriter();
	out.print("<html>");
	out.print("<head>");
	out.print("<meta charset=\"UTF-8\">");
	out.print("</head>");
	out.print("<body>");
	
	out.print("<table border=\"1\" width=\"300\" height=\"400\">");
	out.printf("<tr><td>%d + %d = %d </td></tr>",xx,yy,xx+yy);
	out.printf("<tr><td>%d - %d = %d </td></tr>",xx,yy,xx-yy);
	out.printf("<tr><td>%d * %d = %d </td></tr>",xx,yy,xx*yy);
	out.printf("<tr><td>%d / %d = %d </td></tr>",xx,yy,xx/yy);
	out.print("</table>");
	
	out.print("</body>");	
	out.print("</html>");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
