import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectMain_st {
public static void main(String[] args) throws SQLException{

	//연결 코드만
	String id = "ADMIN";
	String pw = "Chzh!@12942760";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	try {
		
		String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
		con = DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
		System.out.println("연결 성공!");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 검색?");
		String name = sc.nextLine();
		System.out.println(name);
		
		// 지' or 1 = 1 --
		// pstmt << 앞으로 이 친구만 사용하쟈  
		String sql1 =  "select * from student_db where s_name = ?";
		pstmt = con.prepareStatement(sql1);
		pstmt.setString(1,name);
		rs = pstmt.executeQuery();
		
//		//st 
//		String sql2 =  "select * from student_db where s_name = '" + name + "'";
//		System.out.println(sql2);
//		Statement st = con.createStatement();
//		rs = st.executeQuery(sql2);




while (rs.next()) {
			String no = rs.getString("s_no");
			String name1 = rs.getString("s_name");
			String age = rs.getString("s_age");
			String addr = rs.getString("s_addr");
			System.out.printf("%s] %s / %s / %s \n", no,name1,age,addr);
		}
				
		
				
		
	} catch (Exception e) {
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
