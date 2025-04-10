import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMain {

	public static void main(String[] args) {
		// coffee_test_tbl 모든 내용 다출력
		// db ->껍3
		// pk로 딜리트
		
		Scanner sc = new Scanner(System.in);
		
		Connection con= null;
		PreparedStatement pstmt = null;
		
		System.out.println("커피 번호 선택 (숫자): ");
		int no = sc.nextInt();
		System.out.println("커피 가격 얼마로 변경? (숫자) : ");
		int price = sc.nextInt();
		
		String sql = "update coffee_test set c_price =? where c_no =?";
		
		try {
			con = ConnectionMain.connect();
			System.out.println("연결 성공!");
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setInt(2, no);
			
			if (pstmt.executeUpdate()==1) {
				System.out.println("수정 완료~");				

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

