package com.mz.inputoutput;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Reg")
public class Reg extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post방식 한글처리

		// 1. 값 받기
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String gen = request.getParameter("gen");
		String addr = request.getParameter("addr");
		String[] inter = request.getParameterValues("interest");

		System.out.println(name);
		System.out.println(id);
		System.out.println(pw);
		System.out.println(gen);
		System.out.println(addr);
		
		String inter2 = "";
		for (String s : inter) {
			System.out.print(s+" ");
			inter2 += s + " ";
		}
		
		System.out.println(inter2);
		System.out.println("============");

		for (String b : inter) {
			System.out.println(b);
		}
		String introduce = request.getParameter("introduce");
		System.out.println(introduce);

		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<link rel=\"stylesheet\" href=\"reg.css\" />");
		out.print("</head>");
		out.print("<body>");

		out.print("\r\n" + "		<div class=\"container\" style=\"border: 1px solid black; padding: 15px;\">\r\n"
				+ "			<div style=\"border: 1px solid black; padding: 15px;\"> Name\r\n"
				+ 				name + "			</div>\r\n"
				+ "			<div style=\"border: 1px solid black; padding: 15px;\"> ID\r\n"
				+ id + "			</div>\r\n"
				+ "			<div style=\"border: 1px solid black; padding: 15px;\"> PW\r\n"
				+ pw + "\r\n" + "			</div>\r\n"
				+ "			<div style=\"border: 1px solid black; padding: 15px;\"> Gender\r\n"
				+ gen + "\r\n" + "			</div>\r\n"
				+ "			<div style=\"border: 1px solid black; padding: 15px;\"> Addr\r\n"
				+ addr + "	</div>\r\n"
				+ "	<div  style=\" border: 1px solid black; padding: 15px;\"> Interest\r\n"
				+  inter						 + "			</div>\r\n"
				+ "			<div style=\"border: 1px solid black; padding: 15px;\"> Introduce\r\n"
				+ introduce + "			</div>\r\n"
				);

		out.print("</body>");
		out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
