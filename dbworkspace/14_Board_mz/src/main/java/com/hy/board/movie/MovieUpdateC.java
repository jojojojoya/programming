package com.hy.board.movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MovieUpdateC")
public class MovieUpdateC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDAO.getMdao().updateMovie(request);
		System.out.println("컨트롤러넘어옴");
		response.sendRedirect("MovieController");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 등록하는 일
		
		
		// 어디로?
		
		
		
	}

}
