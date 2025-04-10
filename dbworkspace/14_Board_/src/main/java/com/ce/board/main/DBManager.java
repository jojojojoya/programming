package com.ce.board.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.realm.DataSourceRealm;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBManager { // AOP(관점지향)

	
		private static BasicDataSource dataSource;
		static {
			dataSource = new BasicDataSource();
			dataSource.setUrl("jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A");
	        dataSource.setUsername("ADMIN");
	        dataSource.setPassword("Chzh!@12942760");
	dataSource.setMinIdle(10); //최소 유효 커넥션
	        dataSource.setMaxIdle(20); // 최대 유효 커넥션
	        dataSource.setMaxOpenPreparedStatements(100);
	        //풀에서 열린 최대 준비된 sql 문 개수
	        
	      
		}
		public static Connection connect() throws SQLException {
			return dataSource.getConnection();
		}	
		
		
//	public static Connection connect() throws SQLException {
//		
//		// 연결
//		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
//		return DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
//	}
	
	
	public static void close(Connection con , PreparedStatement pstmt, ResultSet rs) {
		
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
