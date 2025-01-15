package com.ce.board.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReviewController")
public class ReviewController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ReviewDAO2.RDAO.dbCon();
//		ReviewDAO.showAllReview(request);
//		ReviewDAO.paging(1, request);
		
		ReviewDAO3.RDAO.showAllReview(request);
		ReviewDAO3.RDAO.paging(1, request);
		
		request.setAttribute("content", "jsp/review/review.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
