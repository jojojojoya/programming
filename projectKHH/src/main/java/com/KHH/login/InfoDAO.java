package com.KHH.login;

import com.KHH.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDAO {

	public static final InfoDAO IDAO = new InfoDAO();
	static Connection con = null;

	private InfoDAO() {
		try {
			con = DBManager.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void infoSearch(HttpServletRequest request) {
		String idName = request.getParameter("idName");
		String idNickName = request.getParameter("idNickName");
		String pwId = request.getParameter("pwMail");
		String pwName = request.getParameter("pwName");

		boolean infoSearcher = idName != null ? true : false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user_account where user_name =? and user_nickname =?";

		// 기본 타겟 데이터
		String targetData = "해당하는 정보를 찾을 수 없습니다.";

		if (infoSearcher) {
			System.out.println("ID 찾기 중");
			System.out.println(idNickName + " " + idName);
		} else {
			sql = "select * from user_account where user_email =? and user_name = ?";
			System.out.println("PASSWORD 찾기 중");
			System.out.println(pwId + " " + pwName);
		}

		try {
			if (con == null) {
				con = DBManager.connect();
			}
			pstmt = con.prepareStatement(sql);

			if (infoSearcher) {
				pstmt.setString(1, idName);
				pstmt.setString(2, idNickName);
			} else {
				pstmt.setString(1, pwId);
				pstmt.setString(2, pwName);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 결과 데이터 처리
				if (infoSearcher) {
					System.out.println(rs.getString("user_email"));
					targetData = "ID는 " + rs.getString("user_email");
				} else {
					System.out.println(rs.getString("user_pw"));
					targetData = "PW는 " + rs.getString("user_pw");
				}
			}

			ResultData rsDt = new ResultData();
			rsDt.setResult(targetData);

			request.setAttribute("info", rsDt);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, rs);
		}
	}
}
