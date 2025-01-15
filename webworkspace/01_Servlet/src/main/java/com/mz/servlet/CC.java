package com.mz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cc")
public class CC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//어노테이션 주의사항 
	// 1. 복붙해도 저건 안바꿔줌. 수동으로 처리.
	// 2. refactor 써도 안바꿔줌. 수동으로 처리
	// 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("CC 테스트!!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
