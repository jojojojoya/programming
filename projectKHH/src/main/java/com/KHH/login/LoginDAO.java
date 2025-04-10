package com.KHH.login;

import com.KHH.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	public static void loginChk(HttpServletRequest request) {

		String userId = request.getParameter("iD");
		String userPassword = request.getParameter("password");
		// 여기서 데이터베이스에서 사용자 인증 로직 구현

		System.out.println(userId);
		System.out.println(userPassword);
		String result = "존재하지 않는 회원";
		String dbPw;

		String sql = "select * from user_account where user_email = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbPw = rs.getString(2);
				if (userPassword.equals(dbPw)) {
					result = "로그인 성공!";
					// bean
					InfoDTO user = new InfoDTO();
					user.setId(userId);
					user.setPassword(dbPw);
					user.setName(rs.getString(3));

//						request.setAttribute("user", user);

					HttpSession hs = request.getSession();
					hs.setAttribute("user", user);
					hs.setMaxInactiveInterval(10000);

				} else {
					result = "pw 에러!";
				}
			}
			System.out.println(result);
			request.setAttribute("result", result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}
}
