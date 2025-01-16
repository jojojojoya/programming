package com.mz.dbweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HC")
public class HC extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			
	// 파라미터가 있다. ! > 없다 /hc?a=
	if (!request.getParameterNames().hasMoreElements()) {
		request.getRequestDispatcher("input.html").forward(request, response);
	} else {
		Model.regPeople(request);
		Model.getAllPeople(request);
		request.getRequestDispatcher("output.jsp").forward(request, response);
		
	}
	
	
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//등록하는 일 - insert C
		request.setCharacterEncoding("utf-8");
			Model.regPeople(request);	
		//전체 조회하는 일 
			Model.getAllPeople(request);
		//어디로?
		request.getRequestDispatcher("output.jsp").forward(request, response);
	}

}
