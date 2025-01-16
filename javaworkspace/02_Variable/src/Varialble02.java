
public class Varialble02 {
	public static void main(String[] args) {
		System.out.println(1);
		
		/*
        
		기본형(primitive type) 변수
		정수형 : byte, short, int, long
		실수형 : float, double
		문자형 : char
		논리형 : boolean

		메모리 공간중 stack 영역에 실제 데이터가 저장
		stack 영역 데이터들은 프로그램 종료시 자동처리됨

		참조형(reference type) 변수
		객체의 참조 값을 나타내는 자료형
		문자열 : String
		실제 값은 heap영역에, stack에는 값이 있는 heap영역의
		번지수가 저장
		heap 영역은 수동으로 정리 해줘야됨 (C언어 등)
		자바는 자동처리됨 => GC (Garbage Collection)

		1.1 정수형 (소수점 없는 숫자)
		byte 127까지, short 32222까지, int 21억 .., long
		보통 int(default)
		// 데이터 용량에 따라 명령어 다름 
		 * > 범위에 맞는 것을 쓰는 것이 좋은 이유 : 메모리 용량이기에 최적화 측면에서 좋음		 * 

		*/
		
		long a = 21000000000L;
		// 숫자를 쓰면 기본적으로 int로 인식하기 때문에, 
		// long 수식 사용 시 L or l (소문자,대문자 무관)을 수치 맨 뒤에 기입
		
		// 1.2 실수형(소수점 있는 숫자)
		// 보통 double(default) 
		float eyeSight = 0.6f;
		double weight = 0.6;
		// 소수는 기본적으로 double로 인식하기 때문에,
		// float 사용 시 f 쓰면 됨
		
		// 하지만, 기본적으로 정수형은 long, 소수형은 double로 사용하기
		
		
		// 1.3 문자형(한 글자)
		char gender = '남';
		System.out.println(gender+"태현");
		
		// 1.4 논리형(참.거짓) true, false
		boolean soldOut = false;
		System.out.println(soldOut);
	
		int b = 10;
		int c = 20; 
		
		int d,e,f; 
		d = 1;
		e = 2;
		f = 3;
		
		
		boolean judge = b < c;
		System.out.println(judge);
		System.out.println(c < b);

		
		// 3. 아무것도 없다
		// 		void  
		
		// 
		
	}
}
