import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

	public static boolean insertUser(User user) {
		boolean result = false;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "INSERT INTO users (id, password, name) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, user.getId());    // ID
            pstmt.setString(2, user.getPw());    // PW
            pstmt.setString(3, user.getName());  // 이름
            
            int row = pstmt.executeUpdate();
            if (row == 1) result = true;
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}