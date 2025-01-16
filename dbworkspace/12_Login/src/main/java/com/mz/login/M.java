package com.mz.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class M {

	public static void login(HttpServletRequest request) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String result = null;

		if (id.equals("mz")) {
			if (pw.equals("1004")) {
				result = "로그인 성공";
			} else {
				result = "로그인 실패";
			}
		} else {
			result = "id 없음";
		}

		request.setAttribute("result", result);

	}
	
	

	public static void login2(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from login_test where l_id = ?";

		
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

}
