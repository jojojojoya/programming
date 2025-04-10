package com.mz.login.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class LoginDAO {

	public static void login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from login_test2 where l_id=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// db tbl 랑 비교
			rs = pstmt.executeQuery();
			String re = "존재하지 않는 회원";
			if (rs.next()) {
				String dbPw = rs.getString(2);
				if (pw.equals(dbPw)) {
					re = "로그인 성공!";
				} else {
					re = "pw 에러!";
				}
			} 
			request.setAttribute("result", re);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

		
	}

}
