package com.mz.pr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertResC")
public class InsertResC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 어디로?
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 등록하는일
		DAO_Res.addRestaurant(request);
		
		// 어디로?
		response.sendRedirect("HC");
//		DAO_Res.selectRestaurants(request);
//		request.getRequestDispatcher("select.jsp").forward(request, response);
	
	}

}
