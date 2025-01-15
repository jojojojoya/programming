import java.util.Scanner;

public class CDMain05_Switch_case {
	public static void main(String[] args) {
			// if (참) else (거짓) <==> 삼항연산자
			// switch case 	<==> if 
		
		 	// jr / 야마노테센 / 다른거
		
		String line = "jr";
				switch (line) {
				case "jr":
					System.out.println("1번 플랫폼");
					break;

				case "야마노테센":
					System.out.println("2번 플랫폼");
					break;
					
				case "다른거":
					System.out.println("3번 플랫폼");
					break;

				default:
					break;
				}
		
		// 입력받을 준비 
		Scanner sc = new Scanner(System.in);
		System.out.println("==================="); 
		System.out.println("몇번 메뉴? (1~4)"); 	
		System.out.println("1. americano"); 	
		System.out.println("2. cafeLatte"); 	
		System.out.println("3. vanillaLatte"); 	
		System.out.println("4. mocaLatte"); 	
		
		
		int num = sc.nextInt();
		switch (num) {
		case 1:
			System.out.println("아메리카노 주문받았습니다");
			break;
		case 2:
			System.out.println("카페라떼 주문받았습니다");
			break;
		case 3:
			System.out.println("바닐라라떼 주문받았습니다");
			break;
		case 4:
			System.out.println("모카라떼 주문받았습니다");
			break;

		default:
			break;
		}
		
		
		System.out.println("=====================");
		// 이병(1), 일병(2), 상병(3), 병장(4)
		// 이병 : 눈치, 부르면 튀어가기, 훈련, 잠
		// 일병 : 부르면 튀어가기, 훈련, 잠
		// 상병 : 훈련, 잠
		// 병장 : 잠
		String grade = "상병";
		
//		System.out.println("눈치");
//		System.out.println("부르면 튀어가기");
//		System.out.println("훈련");
//		System.out.println("잠");
		
		
		// 문자열 비교 ( == 말고 다른거) 구글링
		
		if (grade.equals("이병")) {
			System.out.println("눈치");
			System.out.println("부르면 튀어가기");
			System.out.println("훈련");
			System.out.println("잠");
		} else if (grade.equals("일병")) {
			System.out.println("부르면 튀어가기");
			System.out.println("훈련");
			System.out.println("잠");
		} else if (grade.equals("상병")) {
			System.out.println("훈련");
			System.out.println("잠");	
		} else if (grade.equals("병장")) {
			System.out.println("잠");	
		}
		
		System.out.println("======================");
		// 중복되는 내용을 제거했다 
		switch (grade) {
		case "이병":
			System.out.println("눈치");
		case "일병":
			System.out.println("부르면 튀어가기");
		case "상병":
			System.out.println("훈련");
		case "병장":
			System.out.println("잠");

		default:
			break;
		}
		
		System.out.println("======================");

		// 1~9살 업어주기
		// 20대 술마시러
		// 30대 일열심히 
		// if, switch << 2가지는 서로 대체되는데, 더 유리하거나 깔끔한 식이 존재한다.
		
		int age = 20;
				
		if (age < 40 && age >= 30) {
			System.out.println("일열심히");
		} else if (age < 30 && age >= 20) {
			System.out.println("술마시러");
		} else if (age >= 1 && age <= 9) {
			System.out.println("업어주기");
		}
		
		
		switch (age) {
		case 1,2,3,4,5,6,7,8,9 :
			System.out.println("업어주기");
			break;
		case 20,21,22,23,24,25,26,27,28,29 :
			System.out.println("술마시러");
		break;
		case 30,31,32,33,34,35,36,37,38,39 :
			System.out.println("일열심히");
		break;
		default:
			System.out.println("해당 없음");
			break;
		}

		
		
		
		
}}