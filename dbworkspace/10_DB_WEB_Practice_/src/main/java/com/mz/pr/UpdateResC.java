package com.mz.pr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateResC")
public class UpdateResC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// �����ϴ���
		
			DAO_Res.updateRes(request);

		
			// ����?
			response.sendRedirect("HC");
			
//			DAO_Res.selectRestaurants(request);
//			request.getRequestDispatcher("select.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
