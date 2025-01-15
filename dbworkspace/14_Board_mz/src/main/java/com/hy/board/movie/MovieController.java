package com.hy.board.movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MovieController")
public class MovieController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDAO.getMdao().showAllMovie(request);
		MovieDAO.getMdao().paging(1, request);
		// 전체 영화 조회하는 일
//		MovieDAO.showAllMovie(request);
//		MovieDAO.paging(1,request);
		
		
		request.setAttribute("content", "jsp/movie/movie.jsp");
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
