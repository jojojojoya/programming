package com.hy.board.movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DetailUpdateMovieC")
public class DetailUpdateMovieC extends HttpServlet {
	
	
	private MovieDAO MDAO = 	MovieDAO.getMdao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MDAO.getMovie(request);
		System.out.println("새 페이지 넘어감");
		request.setAttribute("content","jsp/movie/updated_movie2.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//
		request.setCharacterEncoding("UTF-8");
		MDAO.updateMovie2(request);
		response.sendRedirect("MovieController");
		// 어디로?
		
	}

}
