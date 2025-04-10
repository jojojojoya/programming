import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InsertMain {
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 : ");
		String name = sc.next();
		System.out.println("나이 : ");
		String age = sc.next();
		System.out.println("주소 : ");
		String addr = sc.next();
		// 연결
		Connection con = null;
		//
		PreparedStatement pstmt =null; 
		String sql = "insert into student_db values (student_db_seq.nextval, ? , ? , ? )";
		// insert into 테이블이름 values
		try {
			
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, age);
			pstmt.setString(3, addr);
		
			if (pstmt.executeUpdate()==1) {
				System.out.println("등록성공");
			
			}}
			catch (Exception e) {
			e.printStackTrace();
		
}}}