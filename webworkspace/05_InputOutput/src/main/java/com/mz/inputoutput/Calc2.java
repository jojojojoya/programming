package com.mz.inputoutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Calc2")
public class Calc2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int xx = Integer.parseInt(request.getParameter("xx"));
		int yy = Integer.parseInt(request.getParameter("yy"));
	
	String[] op = request.getParameterValues("oper");
	response.setCharacterEncoding("UTF-8");
	PrintWriter out = response.getWriter();
	out.print("<html>");
	out.print("<head>");
	out.print("<meta charset=\"UTF-8\">");
	out.print("</head>");
	out.print("<body>");
	
	out.print("<table border=\"1\" width=\"300\" height=\"400\">");

	for (String s : op) {
		if (s.equals("+")) {
			out.printf("<tr><td>%d + %d = %d </td></tr>",xx,yy,xx+yy);
	}	else if (s.equals("-")) {
		out.printf("<tr><td>%d - %d = %d </td></tr>",xx,yy,xx-yy);
	}	else if (s.equals("x")) {		
		out.printf("<tr><td>%d * %d = %d </td></tr>",xx,yy,xx*yy);
		
	} else {
		if (yy != 0) {
			double result = (double)xx / yy;
			DecimalFormat df = new DecimalFormat("#.##");
			String test = df.format(result);
			out.printf("<tr><td>%d / %d = %d </td></tr>",xx,yy,result);
	}else {
		out.print("0으로 나눌 수 없어요");		}
	}
	
	}
	out.print("</table>");		
	
	out.print("</body>");	
	out.print("</html>");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
