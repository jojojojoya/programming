package com.hy.board.movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MoviePageC")
public class MoviePageC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 일
		int p = Integer.parseInt(request.getParameter("p"));
		MovieDAO.getMdao().showAllMovie(request);
		MovieDAO.getMdao().paging(p,request);
		// 어디로? 
		
		request.setAttribute("content", "jsp/movie/movie.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
