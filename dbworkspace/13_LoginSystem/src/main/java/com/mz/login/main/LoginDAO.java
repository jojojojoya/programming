package com.mz.login.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginDAO {
	
	
	public static void loginCheck(HttpServletRequest req) {
		UserDTO u = (UserDTO) req.getSession().getAttribute("user");
		System.out.println(u);
		if (u==null) {
			req.setAttribute("loginPage", "login,jsp");
			
		}
		else {
			req.setAttribute("loginPage","loginOK.jsp");
		}
	}

	public static void login(HttpServletRequest request) {
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from login_test2 where l_id = ?";

			
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				String result = null;
				if(rs.next()) {
					
					String dbPw = rs.getString(2);
					
						if (pw.equals(dbPw)) {
							result = "로그인 성공";
							UserDTO user = new UserDTO();
							user.setId(rs.getString("l_id"));
							user.setPw(dbPw);
							user.setName(rs.getString(3));
							
//							request.setAttribute("user", user);
							HttpSession hs = request.getSession();
							hs.setAttribute("user", user);
							hs.setMaxInactiveInterval(10);
							
						
						} else {
							result = "로그인 실패";
						}
					} else {
						result = "id 없음";
					}
				
				request.setAttribute("result", result);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(con, pstmt, rs);
			}
			

			//db에 있는 아이디랑 비교 
			//dp에 있는 걸 불러온다.


		}

	public static void logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		// 로그아웃 일 >> 만들어진 유저 세션 없애기
		
		HttpSession hs = request.getSession();
//		hs.setAttribute("user", null);
		hs.removeAttribute("user");
//		hs.invalidate();
		
		loginCheck(request);
	}

	
	}

