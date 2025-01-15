package com.ce.board.movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateMovieController")
public class UpdateMovieController extends HttpServlet {
	
	private MovieDAO MDAO = MovieDAO.getMdao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		MovieDAO.getMdao().dbCon();
//		MovieDAO.getMdao().showMovieDetail(request);
		MDAO.showMovieDetail(request);
		
		request.setAttribute("content", "jsp/movie/movie_update2.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		MovieDAO.updateMovie1(request);
		MovieDAO.getMdao().updateMovie2(request);
		
		System.out.println(request.getParameter("no"));
		
		response.sendRedirect("MovieDetailController?no="+request.getAttribute("no"));
	}

}
