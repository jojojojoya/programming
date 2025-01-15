import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBMain_Modi {

	public static void main(String[] args) {
			//db _test tbl 	
			// db 관련 -> 껍 3 
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 : ");
		String name = sc.next();
		System.out.println("나이 : ");
		int age = sc.nextInt();
		
		

		Connection con = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		String id = "ADMIN";
		String pw = "Chzh!@12942760";
		String sql = "insert into db_test values(db_test_seq.nextval,?,?)";
		
		try {
			
			
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			// 물음표 채우기 -> 문장 완성
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
					
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록성공");
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
}
}
