package com.hy.board.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ReviewRegC")
public class ReviewRegC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	// 어디로?
	request.setAttribute("content", "jsp/review/review_reg.jsp");
	request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//등록하는일
		ReviewDAO.RDAO.addReview(request);
		response.sendRedirect("ReviewC");
	}

}
