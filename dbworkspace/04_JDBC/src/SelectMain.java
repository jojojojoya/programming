import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectMain {
public static void main(String[] args) throws SQLException{

	//연결 코드만
	String id = "ADMIN";
	String pw = "Chzh!@12942760";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
		System.out.println("연결 성공!");
		
		String sql =  "select * from student_db";
		pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("s_no");
			String name = rs.getString("s_name");
			String age = rs.getString("s_age");
			String addr = rs.getString("s_addr");
			System.out.printf("%s] %s / %s / %s \n", no,name,age,addr);
		}
				
		
				
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
}
