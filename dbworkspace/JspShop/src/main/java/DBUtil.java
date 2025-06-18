import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver"); 
		 String url = "jdbc:mariadb://localhost:3306/mydb"; // DB 주소
        String user = "root";     // 사용자 이름
        String password = "1234"; // 비밀번호
        return DriverManager.getConnection(url, user, password);
    }
}