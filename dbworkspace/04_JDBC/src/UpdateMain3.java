import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UpdateMain3 {
 public static void main(String[] args) {
	//test 
	 // 리스트업 리스트에서 선택한 그 사람의 나이만 바뀔수 있게 
	 // @ 00님의 나이가 00살로 수정되었습니다. 
	

	 Scanner sc = new Scanner(System.in);
	 String sql1 = "select * from student_db";
	 // dto => 데이터 전송객체 (data transfer object)
	 
	 
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 
	 String sql2 = "update student_db set s_age =? where s_no = ?";
	 // 수정 update, set, where
	 
	 
	 try {
			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
			System.out.println("연결 성공!");
			
			pstmt = con.prepareStatement(sql1);
			Map<Integer, String> vals = new HashMap<Integer, String>();
			
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString(1) + "]" + 
			rs.getString(2) + " " + 
				rs.getString(3) + " " + 
				rs.getString(4) + " "  );
				vals.put(rs.getInt("s_no"), rs.getString("s_name"));
			}	

			System.out.println("수정할 학생의 번호? : ");
			int no = sc.nextInt();
			System.out.println("수정할 학생의 나이? : ");
			int age = sc.nextInt();
			pstmt.close();
			
			
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, age);
			pstmt.setInt(2, no);
		
			if (pstmt.executeUpdate()>=1) {
			System.out.println("수정완료");
			System.out.println(vals.get(no) + " 님의 나이가 " + age + "살로 수정되었습니다.");
					
				}
				
			
//		
//			if (pstmt.executeUpdate()>=1) {
//				Scanner sc = new Scanner(System.in);
//				System.out.println("수정할 사람의 이름?");
//				String name = sc.next();
//				System.out.println("몇살로 수정할까요?");
//				String age = sc.next();
//				System.out.println("수정 완료~"
//						+ "");
			
//			}
	 // 유저가 입력한 값으로 수정 / pstmt의 결과가 resultset으로 반환


				}
				catch (Exception e) {
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
				
	}}