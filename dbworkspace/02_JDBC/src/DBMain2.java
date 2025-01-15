import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBMain2 {
public static void main(String[] args) {

	String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
	String id = "ADMIN";
	String pw = "Chzh!@12942760";
	
	String sql = "select * from snack order by s_name";
	
	try {
		//연결	
		Connection con = DriverManager.getConnection(url, id, pw);
		//실행도구	
		Statement st = con.createStatement();
		//결과
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			String no = rs.getString(1);
			String name = rs.getString(2);
			String maker = rs.getString(3);
			int price = rs.getInt(5);
			System.out.println("품번 : " + no);
			System.out.println("품명 : " + name);
			System.out.println("회사 : " + maker);
			System.out.println("가격 : " + price);
			System.out.println("========================");
			
		}
		
		
		rs.close();
		st.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
