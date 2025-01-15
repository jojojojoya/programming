import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBMain {
	public static void main(String[] args) {
		
		
		// 연결
		
		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		String id = "ADMIN";
		String pw = "Chzh!@12942760";
		
		
		try {
			Connection con = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 성공!");
			
			// 실행도구
			Statement st = con.createStatement();
			String sql = "select *from snack";	
			
			// 결과얻기 
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				System.out.println(rs.getString("s_name") + "/" + rs.getInt("s_price"));
			}
			 // 아래 행으로 내린다. CSR
			
			
			
			rs.close();
			st.close();
			con.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		
	}
	
}
