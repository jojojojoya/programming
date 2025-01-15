// 함수, 기능
// 관련있는 작업을 한데 묶어 정의해놓고 필요할 때 불러다 씀 

/* 
 	1. 함수 정의 
 	public static void 함수명() {
 	호출 시 실행할 코드 }
 	
 	1) 함수의 인자(argument) , 매개변수(parameter) : 함수 수행에 필요한 것들 
 	public static void 함수명22 (자료형 변수명, 자료형 변수명, ... (param) { 호출당하면 여기 실행 } 
 	2) 위에꺼 호출 
 	함수명 22 (5); 여기서 숫자 5가 인자 (argument) : 실제 그 값
 	
  */


public class FMain {
	
	public static void add(int a, int b) /* <--- 파라미터 */ {
			System.out.println(a+b);
		}
	public static int add1(int a, int b) {
		return a+b; //return이 있으면 void 일수 없고, 돌아갈 자료형이 있어야함
	}

	public static void pushUp(int cnt) {
			System.out.println("=========");
			for (int i = 0; i < cnt; i++) {
				System.out.println("엎드려");
		System.out.println("팔굽혀");
		System.out.println("팔펴");
			}
	}
	
	
	public static void main(String[] args) {
		//  main은 명령어 메소드 함수였음 
		info();
		add(10, 20);
		System.out.println(add1(5,5));
		int a = add1(1,2);
		System.out.println(a);
			pushUp(10);
			info();
			pushUp(3);
			sport("축구");
			
			
		}
			public static void sport(String what) 	{
				// 축구, 농구 
				System.out.println("공 챙겨서");
				if (what.equals("축구")) {
					System.out.println("축구공 챙겨서 운동장");					
				} else if (what.equals("농구"))
					System.out.println("농구공 가지고 강당으로");
			System.out.println("공 반납");
			}

		
		
	
	
	// 호출하면, 이름을 출력해주는 메서드를 생성
	public static void info() {
		System.out.println("*****");
		System.out.println("이름 : 수진");
		System.out.println("*****");
		
		
		// 메서드 명령의 위치는 딱히 상관 없음 


}
	
	

	}
