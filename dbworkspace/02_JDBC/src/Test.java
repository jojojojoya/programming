
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args) {
		
		// jdbc - java -> db 컨트롤 하겠다.
		// db 관련 작업? 껍데기 시리즈를 써야함 - con,st,rs > connection, statement, resultSet
		// 드라이버가 없는 건 jar가 없다는 말임

		
		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		String id = "ADMIN";
		String pw = "Chzh!@12942760";
		
		String sql = "select * from db_test";
		// 연결 
		try {
			Connection con = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 성공");
			// 실행 도구 
			Statement st = con.createStatement();
			// 결과 얻기
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name =	rs.getString("d_name");
				int age =  rs.getInt("d_age");
				
				System.out.println(name + " / " + age);
				
			} // false를 맞을때까지 while은 계속 돌린다. 
			
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}
		
}
