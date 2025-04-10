
public class Operator02 {
	public static void main(String[] args) {

		// 논리(비교) 연산자 : 결과로 boolean 나오는 거 (참/거짓)
		// >(초과), <(미만), >=(이상), <=(이하), ==(같다), !=(다르다)

		int x = 10;
		int y = 20;

		boolean a = x != y; // 다르니까 맞다
		System.out.println(a);

		// 결합 연산자 : 논리 연산자 여러개 묶는거
		// && (AND) : a && b => a랑 b를 모두 만족하면 true
		// || (OR) : a || b => a나 b를 만족하면 true
		// ^ (XOR) : a ^ b => a나 b 둘중 하나만 만족하면 true

		// 단항 연산자
		// !(NOT) : !a => a의 결과를 반대로

		// x가 5보다 크고, y가 10보다 작나?
		boolean b = x > 5 && y < 10;
		System.out.println(b);

		// x가 5보다 크거나, y가 10보다 작나?
		boolean c = x > 5 || y < 10;
		System.out.println(c);

		System.out.println("------------------------------");

		// 놀동에서 놀이기구 타도되는지 체크하는 프로그램 있다
		// 키, 나이

		int height = 180;
		int age = 30;
		System.out.println("------------------------------");

		// 나이 10살 이상이고 키 200넘으면 타도됨
		boolean ok = age >= 10 && height > 200;
		System.out.println(ok);

		// 키가 200 넘고, 나이가 10살 이상이면 타도됨
		// ******* 확률이 낮을 걸 앞으로 배치하면 연산횟수를 줄일 수 있음
		boolean ok4 = height > 200 && age >= 10;
		System.out.println(ok4);

		System.out.println("------------------------------");

		// 나이 10살 이상이거나 키 200넘으면 타도됨
		// ******* 확률이 높은 걸 앞으로 배치하면 연산횟수를 줄일 수 있음
		boolean ok2 = age >= 10 || height > 200;
		System.out.println(ok2);

		// 키가 200 넘거나, 나이가 10살 이상이면 타도됨
		boolean ok3 = height > 200 || age >= 10;
		System.out.println(ok3);
		
		// 키가 200이 넘으면 타면 안됨
		height = 220;
		boolean ok6 = !(200 < height);
				System.out.println(ok6);
				
				
		// ****중요***** 삼항연산자 (참 / 거짓 개념)
				// 둘 중 하나 
		// 조건식 ? 조건 만족시 값(true) : 조건 불만족시 값(false)
				// 조건식 ? true : false
		// 나이가 8살이 넘으면 welcome, 안넘으면 go home	
		
				String ok10 = age > 8 ? "welcome" : "go home";  
				System.out.println(ok10);
		// 삼항 연산자 개발자들이 많이 쓴다. 

				
				
		// 짝수, 홀수 
				System.out.println(age % 2 == 0); // % = 나눈 나머지 = 0
				// 짝수를 구하는 방법
				System.out.println(age % 2 == 1); // % = 나눈 나머지 = 1
				// 홀수를 구하는 방법
				
				
		// TEST 

				// 조건을 먼저 서술
		// 입장료 : 키가 200 넘거나 나이가 20이상이면 10000원, 아니면 5000원
				int ip = height > 200 || age >= 20 ? 10000 : 5000; 		
		// 할인 : 나이가 짝수면 1000원, 아니면 500원할인
				int dc = age % 2 == 0 ? 1000 : 500;
		// 총요금 : 입장료 - 할인
				int tt = ip - dc;
				
				
				// 결과출력 
				
				// 입장료 : 00원
				// 할인 : 00원
				// 총요금 : 00원
		
		System.out.println("입장료 : " + ip + "원");
		System.out.println("할인 : " + dc + "원");
		System.out.println("총요금 : " + tt + "원");
		
	
				

				
				

	}
	
}
