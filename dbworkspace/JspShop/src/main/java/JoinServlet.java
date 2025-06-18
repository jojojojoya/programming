import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        
        User user = new User();
        user.setId(id);
        user.setPw(pw);
        user.setName(name);
        
        boolean result = UserDAO.insertUser(user);
        
        if (result) {
        	response.sendRedirect("success.jsp");
			
		} else {
        response.sendRedirect("join.jsp");
	}
}}
