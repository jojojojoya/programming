
public class OCMain03 {
	public static void main(String[] args) {
		// TEST 
		
		// 프로그래머
			// 이름, 나이, 야근여부 
			// 일하는 기능
			// 정보출력 기능

		// 오버로딩 생성자로 객체 생성
			// 객체 생성 후 정보 출력
		
		// syso(p1.work("일"); => 개발하기 출력
		// syso(p1.work("퇴근"); => ㄳ 출력
		// @p1.work (); => 콘솔에 ㅋㅋ.. 출력
		
		Work p1 = new Work("누구",20, true);
		p1.printInfo(); 
		System.out.println(p1.work("일")); //왜 p1??
		System.out.println(p1.work("퇴근")); //왜 p1??
		
		p1.work(); //zz....
		
		
	
	
	}
	
}
