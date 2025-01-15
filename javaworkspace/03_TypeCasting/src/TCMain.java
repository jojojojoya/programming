
public class TCMain {
	public static void main(String[] args) {
		// 형 변환 (type casting)
			// 그릇의 종류를 바꾸는거
		
			// 기본 자료형
			// 정수 : int
			// 실수 : double

		// int랑 int연산 : 결과는 int
		// int랑 double연산 : 결과는 double
			// int + double = double = int 연산 같음

		double a = 10 / (double)4;		// 2.5
		System.out.println(a);
		
		// int 21억~~ long 그것보단 큰거 
		int aa = 10;
		long bb = aa;	// int > long으로 변환했다
		
		System.out.println(bb);
		
		int cc = (int) bb; // long > into 변환
		// 명시적, 묵시적 형변환이라는 개념 
		// 명시적 형변환 : 큰 데이터 타입을 작은 데이터 타입으로 변환. 데이터 손실 방지
		
		
		
		
		///// 여기부터 못들으심
		
		double dd = cc; // int > double 
		int ee = (int) dd; 	// double > int
		// 마우스 에러줄 형변환 가능
		// 맞는 타입으로 캐스트
		
		int b = 5;
		
		System.out.println(ee+b);
		System.out.println("-------");

		// string + ? = string 
		String ff = ee + "";  		// "" >> 빈문자열 empty string
	 	System.out.println(ff);
	 	System.out.println(ff + b); // 문자 10이랑 숫자 5가 붙어서 나온거긔
		 // 설명필요
		
		
	}
}
