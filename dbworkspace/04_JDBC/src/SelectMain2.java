import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectMain2 {
public static void main(String[] args) throws SQLException{
	// 나이 검색 ex) 20 이하 사람만 검색
	// 이름 검색 ex) 성민 이름만 치면 그사람의 내용만 나오게 
	
	
	//연결 코드만
	String id = "ADMIN";
	String pw = "Chzh!@12942760";

	Scanner sc = new Scanner(System.in);
//	System.out.println("입력 나이 이하 검색 :");
//	int age2 = sc.nextInt();
	System.out.println("이름 검색 :");
	String name2 = sc.next();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
		System.out.println("연결 성공!");
		
		
		
//		String sql =  "select * from student_db where s_age <= ?";
		String sql =  "select * from student_db where s_name = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name2);
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
