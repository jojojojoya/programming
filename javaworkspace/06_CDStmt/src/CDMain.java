

// Conditional Stmt (조건문) 
	// ★★★ 어떤 언어든 간에 조건문이 필수다.
// if (condition) {
//	참이면 실행 
//			
//		}


public class CDMain {
	public static void main(String[] args) {
	// if, else문 (둘 중 하나, 맞냐 아니냐)
		int age = 50;
		
		// 나이가 70 이상이면 "안녕하십니까" 아니면 "안녕하세요" 출력
		if (age >= 70) {
			System.out.println("안녕하십니까");
		} else {
			System.out.println("안녕하세요");

			// 위와 같은 경우에 삼항연산자와 대체 가능 
		}
		String answer = age >= 70 ? "안녕하십니까" : "안녕하세요";
		System.out.println(answer);
		
		
			// 나이가 홀수이고, 10살 미만이면 "ㅋㅋㅋ" 아니면 "ㅎㅎㅎ" 출력
		int age2 = 3; // 변수에 수를 부여 
		
			if (age2 % 2 == 1 && age2 < 10) {				
				System.out.println("ㅋㅋㅋ"); // 출력
			} else {
				System.out.println("ㅎㅎㅎ"); // 출력
			}
		
		String answer2 = age2 % 2 == 1 && age2 < 10 ? "ㅋㅋㅋ" : "ㅎㅎㅎ";
		System.out.println(answer2);
		
		
		// 나이가 1살 초과, 5살 미만이면 "ㅎㅅㅎ" 출력
		if (age2 > 1 && age2 < 5) { 
			// 1< age2 < 2 계산식은 적용 안됨  
			System.out.println("ㅎㅅㅎ");
		} 
		
		
		// 나이가 1살 초과, 5살 미만이면 "ㅎㅅㅎ" 출력
		String answer3 = age2 > 1 && age2 < 5 ? "ㅎㅅㅎ" : "";
		System.out.println(answer3);
		
		// 성인 & 아이 동반탑승 가능?
		
		
		// age '50' , age2 '3'를 활용 
		int height = 100; // age2 3살의 키 
		
		// age가 20살 넘거나, age 2가 3살 이상이고, 키가 80 넘으면 
		// "동반탑승 가능" 출력
		// 조건 안맞으면 "탑승 불가" 출력
		
		
		
		if (age > 20 || (age2 >= 3 && height > 80)) {
			System.out.println("동반탑승 가능");
			
		}else {System.out.println("탑승 불가");
			
		}
		
		
		// t || t && t  => t
		// t || t && f  => t
		// t || f && t  => t
		// t || f && f  => t
		// f || f && f  => f
		
		// >> 연산자도 우선순위가 있다.
		// ★★★★ &&가 연산 순위 더 높음 > &&부터 연산시작.
		
		
		
	}
}
