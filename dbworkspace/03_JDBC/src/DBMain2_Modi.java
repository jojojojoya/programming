import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBMain2_Modi {
public static void main(String[] args) {
	//snack tbl에 과자 등록하기
	
	// 이름, 회사, 가격, 무게, 제조일자(sysdate)
	// 가이드 
	// db? - 껍 3- 2개 (rs 필요x), pk에 있는 no 카운팅은 시퀀스로 진행
	
	Scanner sc = new Scanner(System.in);
	System.out.println("이름 : " );
	String name = sc.next();
	
	System.out.println("회사 : " );
	String maker = sc.next();
	System.out.println("가격 : " );
	int price  = sc.nextInt();
	System.out.println("무게 : " );
	double weight  = sc.nextInt();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	String url = "jdbc:oracle:thin:@gepoksg3dgxk0n4a_medium?TNS_ADMIN=C:/Users/jien9/Downloads/Wallet_GEPOKSG3DGXK0N4A";
	String id = "ADMIN";
	String pw = "Chzh!@12942760";

	String sql = "insert into snack values(snack_seq.nextval,?,?,?,?, sysdate)";

	try {
		
		con = DriverManager.getConnection(url, id, pw);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, maker);
		pstmt.setDouble(3, weight);
		pstmt.setInt(4, price);
		if (pstmt.executeUpdate() == 1) {
			System.out.println("등록 성공!");
		
	} }
		catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			
			pstmt
			.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		// 쌍따옴표 쓰기 귀찮을때 쓴다.
	}
	
	
	
	
}

