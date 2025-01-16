import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMain {
public static Connection connect() throws SQLException {
	//연결 코드만
	String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
	return DriverManager.getConnection(url, "ADMIN", "Chzh!@12942760");
}
}
