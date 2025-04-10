import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteMain2 {
	public static void main(String[] args) {
		// 이름을 입력받으면 그 사람 삭제 (동명이인이면 모두 삭제)
		
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 학생의 이름? : ");
		String name = sc.next();
		
		// 연결
		Connection con = null;
		//
		PreparedStatement pstmt =null; 
		String sql = "delete student_db where s_name = ? ";
		
		try {
			
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
		
			if (pstmt.executeUpdate() >=1) { // 여러 데이터라면 >=1으로 업데이트된 데이터를 확인하고 다중처리
				System.out.println("등록성공");
			

			}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}}}}
			
			