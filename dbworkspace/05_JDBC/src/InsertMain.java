import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertMain {

	public static void main(String[] args) {
		// coffee_test_tbl 모든 내용 다출력
		// db ->껍3
		// 등록
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		
		String sql = "insert into coffee_test values(coffee_test_seq.nextval,?,?,?)";
		try {
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			
			pstmt = con.prepareStatement(sql);
			System.out.println("이름입력");
			String name = sc.next();
			pstmt.setString(1, name);
			System.out.println("가격입력");
			String price = sc.next();
			pstmt.setString(2, price);
			System.out.println("원산지 입력");
			String made = sc.next();
			pstmt.setString(3, made);
		
			if (pstmt.executeUpdate()==1) {
				System.out.println("등록성공");
		}}
			catch (Exception e) {
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

