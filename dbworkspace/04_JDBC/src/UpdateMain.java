import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMain {
 public static void main(String[] args) {
	//test 
	 // 수정할 사람의 이름?
	  // 을 치면 몇살로 수정할까요?
	 // ex) 민재, ex) 10
	 
	 Scanner sc = new Scanner(System.in);
	 System.out.println("수정할 사람의 이름?");
	 String name = sc.next();
	 System.out.println("몇살로 수정할까요?");
	 String age = sc.next();
	 // 유저가 입력한 값으로 수정 / pstmt의 결과가 resultset으로 반환
	 // dto => 데이터 전송객체 (data transfer object)
	 
	 
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 
	 String sql = "update student_db set s_age =? where s_name = ?";
	 // 수정 update, set, where
	 
	 
	 try {
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, age);
			pstmt.setString(2, name);
		
			if (pstmt.executeUpdate()>=1) {
				System.out.println("수정 완료~"
						+ "");
			
			}}
			catch (Exception e) {
			e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
}}
// rs = getString, pstmt = setString 
