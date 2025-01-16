import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMain {
public static void main(String[] args) throws SQLException{

	//연결 코드만
	String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
	String id = "ADMIN";
	String pw = "Chzh!@12942760";


	Connection con = DriverManager.getConnection(url, id, pw);
	System.out.println("연결 성공!");
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
}
