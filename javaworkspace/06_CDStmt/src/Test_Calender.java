import java.util.Scanner;

public class Test_Calender {
	public static void main(String[] args) {

		// 각 월이 몇일까지 있는지 알려주는 프로그램
		// 31일까지 있는 달 1,3,5,7,8,10,12
		// 30일까지 있는 달 4,6,9,11
		// 28일까지 있는 달 2
		
		// eg. 유저가 1이라는 월 입력 > "31일 까지"
		// 사용자한테 월 입력 받기
		
		// 판정 > 
		
		// 결과출력 
		// 0월은 00일 까지 입니다.
			// @ 월에서 1~12 벗어난 숫자는 "입력오류" 출력

		System.out.println("월을 입력하시면 일수를 알려드려요^^");
		
		
		Scanner sc = new Scanner(System.in);
		int month = sc.nextInt();
		
		System.out.printf("%d월은 ",month);
		
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			System.out.println("31일 까지입니다.");
		} else if (month == 4 || month == 6 || month == 9 || month == 11 ) {
			System.out.println("30일 까지입니다.");
		} else if (month == 2 ) {
			System.out.println("28일 까지입니다.");
			
		}else {
			System.out.println("입력오류");
			// value 값이 타겟팅 되어 있을때는 switch가 좋음
			// || > or 연산자 >> ^ 요걸로도 치환 가능		
			
			
			// 
			// switch (month) {
			// case 1,3,5,7,8,10,12
			// syso 31일까지 출력하고 순차적으로 가면 됨.
			
		}
		
		
		
	}
}
