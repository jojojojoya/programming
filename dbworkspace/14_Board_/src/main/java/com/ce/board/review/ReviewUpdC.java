package com.ce.board.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReviewUpdC")
public class ReviewUpdC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ReviewDAO3.RDAO.getReviewDetail(request);
		request.setAttribute("content", "jsp/review/review_update.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewDAO3.RDAO.updReview(request);
		// request.setAttribute("content", "jsp/review/review_detail.jsp");
		
		response.sendRedirect("ReviewDetailController?no=" + request.getParameter("no"));
	}

}
