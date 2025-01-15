import java.util.Scanner;

public class Test_Login {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// login system 
		// id ,pw 
		// db에 실제 존재하는 idpw 있는게 맞는데 > 임의 생성 및 테스트 
		String dbid = "sj";
		String dbpw = "1004";
		
		// 입력 받기
		// 판정 
			// 둘 다 맞으면 "로그인 성공"
			// id 틀리면 "ID 에러 - 존재하지 않는 회원입니다."
			// pw 틀리면 "PW 에러 - 비밀번호가 일치하지 않습니다."
		
		System.out.println("id를 입력하세요.");
		String ipid = sc.next();

		System.out.println("비밀번호를 입력하세요.");
		String ippw = sc.next();
		
		// equals >> 문자열비교 시 (변수, equals(값)) 
		// 맞지 않다면 변수 비교 앞에 !하긔
		if (ipid.equals(dbid) && ippw.equals(dbpw)) {			
			System.out.println("로그인 성공");
		} else if (ipid.equals(dbid)) {
			System.out.println("PW 에러 - 비밀번호가 일치하지 않습니다.");
			
		} else if (ippw.equals(dbpw)) {
			System.out.println("ID 에러 - 존재하지 않는 회원입니다.");
			
		} else { 
			System.out.println("로그인 실패");
		}
	}
}

// if (ipid.equals(dbid)) {			
// 	if (ippw.equals(dppw))
// {System.out.println("로그인 성공");
// } else { ("pw 에러")) {
// } else {
//	System.out.println("ID 에러");


