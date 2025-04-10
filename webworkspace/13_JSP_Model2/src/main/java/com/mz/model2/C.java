package com.mz.model2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// C(controller) - 교통정리 
// 상황판단을 해서 필요한 쪽으로 보냄 
// 웹 사이트  진입점 (실행을 여기서)


@WebServlet("/C")
public class C extends HttpServlet {
// 주소를 쳐서 접속하거나 클릭해서 들어감 - GET
// 어떤 웹사이트에 처음 들어가는 경우 - GET
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("v1.html");
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 로직처리는 M에
		M.calc(request);
		// v2.jsp에 결과를 보여줘야됨.
		request.getRequestDispatcher("v2.jsp").forward(request, response);
	
	
	}

}
