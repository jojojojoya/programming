package com.mz.login.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	public static Connection connect() throws SQLException {
		// 로컬
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		// 클라우드
		String url = "jdbc:oracle:thin:@sdcwbitetw4a27kz_medium?TNS_ADMIN=F:/sbt10/Wallet_SDCWBITETW4A27KZ";
		return DriverManager.getConnection(url, "MZ1004", "Soldesk802!!");
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if (rs != null) {
				rs.close();
			}
			pstmt.close();
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
