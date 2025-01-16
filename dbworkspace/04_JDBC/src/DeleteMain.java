import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DeleteMain {
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("번호 : ");
		int no = sc.nextInt();
		
		// 연결
		Connection con = null;
		//
		PreparedStatement pstmt =null; 
		String sql = "delete student_db where s_no = ? ";
		
		try {
			
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
		
			if (pstmt.executeUpdate()==1) {
				System.out.println("등록성공");
			
			}}
			catch (Exception e) {
			e.printStackTrace();
		
}}}