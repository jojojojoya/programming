import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectMain {

	public static void main(String[] args) {
		// coffee_test_tbl 모든 내용 다출력
		// db ->껍3
		// pk로 딜리트
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from coffee_test";
		String sql2 = "delete coffee_test where c_no";
		try {
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString(1) 
					
						+ " " + rs.getString(2)+ " " + 
						rs.getString(3)+ " " + 
						rs.getString(4)); }
			
			Scanner sc = new Scanner(System.in);
			System.out.println("몇번 메뉴 취소?");
			int no = sc.nextInt();
			
		

		pstmt.close();
		pstmt = con.prepareStatement(sql2); // 연결 교체
		pstmt.setInt(1, no);
		
		if (pstmt.executeUpdate() == 1) { // 여러 데이터라면 >=1으로 업데이트된 데이터를 확인하고 다중처리
			System.out.println("삭제 성공");
		}}
			catch (Exception e) {
			e.printStackTrace();
		} finally {
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

