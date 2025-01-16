import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMain {
		public static void main(String[] args) {
			
			
			// db_test tbl < select (Read) 
			// db 작업 - 껍데기 (3종)
			// CRUD - R(Select) => rs(필요)
			// CUD - (insert, update, delete) => rs(필요하지 않음)
			
			// executeQuery() = rs (select) (R) << Read : rs. next 라는건 다음행을 읽어라라는 뜻
			// executeUpdate() = 나머지 CUD 활용할때 진행 
				// 프로젝트를 새로 생성할때마다 코드를 실행할 수 있는 oracle DB의 jar 패치 필요
			

			String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
			String id = "ADMIN";
			String pw = "Chzh!@12942760";
			
			
			String sql = "insert into db_test values(db_test_seq.nextval,'jh',10)";
			Connection con = null;
			Statement st = null;
			
			try {
				con = DriverManager.getConnection(url, id, pw);
				System.out.println("연결성공");
				st = con.createStatement();

				
				int row = st.executeUpdate(sql);
				if (row == 1) {
					System.out.println("등록 성공!");
					
				}
				
				

				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}}
}

