import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectMain3 {
public static void main(String[] args) throws SQLException{
	// 나이 검색 ex) 해당 나이 이상으로 하고 싶은지 이하로 하고 싶은지 선택하게 하고, 그 나이를 검색하게 하면 그 기준에 따라 검색
	// 이름 검색 ex) 성민 이름만 치면 그사람의 내용만 나오게 
	
	
	//연결 코드만
	String id = "ADMIN";
	String pw = "Chzh!@12942760";

	Scanner sc = new Scanner(System.in);
	System.out.println("나이 검색 :");
	int age = sc.nextInt();
	System.out.println("해당 나이 기준으로 어떻게 찾아드릴까요? 1: 이상 2: 이하 ");
	int ans = sc.nextInt();
//	System.out.println("이름 검색 :");
//	String name2 = sc.next();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
		System.out.println("연결 성공!");
		
//		[선생님 버전]
//		String sql =  "select * from student_db where s_age";
//		if (ans == 1) {
//			sql += " >=?";
//		} else if (ans ==2) {
//			sql += " <=?";
//			
//		}
//		pstmt = con.prepareStatement(sql);
//		pstmt.setInt(1, age);
//				rs = pstmt.executeQuery();
		//		String sql =  "select * from student_db where s_name = ?";
		if (ans == 1) {
			String sql =  "select * from student_db where s_age >= ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, age);
				rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("s_no");
			String name = rs.getString("s_name");
			String age1 = rs.getString("s_age");
			String addr = rs.getString("s_addr");
			System.out.printf("%s] %s / %s / %s \n", no,name,age1,addr);
		}
				
		} else {
			String sql =  "select * from student_db where s_age <= ?";
		
				
		
		}	} catch (Exception e) {
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
