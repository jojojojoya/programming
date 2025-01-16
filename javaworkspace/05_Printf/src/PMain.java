
public class PMain {
	public static void main(String[] args) {
		// ★★★★★★★★★★★★printf : 출력 형식 잡을 때 사용 
		// %d : 정수형 변수 값 들어올 자리 - %03d : 3자리수 출력 ex) 001
        // %f : 실수형 변수 값 들어올 자리 - %.2f : 소수점 2자리 출력 ex) 1.23
        // %s : String 변수 값 들어올 자리
		
		
		int a = 1;
		double b = 1.23456;
		String c = "abc";
		
		System.out.printf("%02d\n", a);
		System.out.printf("%.1f", b);
		// 언제 유용하게 쓸수 있는가? 소수 표현 시 
		System.out.printf("\n%s",c);
		
		// a는 00이고, b는 00이고, c는 00이다.
		System.out.println("a는 " + a + "이고, b는 " + b + "이고, c는 " + c + "이다.");
		System.out.printf("a는 %d이고, b는 %f이고, c는 %s이다.", a,b,c); 
		
		// a,b,c,의 순서가 다르면 안됨
		// a는 2자리, b는 소수점 1자리
		System.out.printf("a는 %02d이고, b는 %.1f이고, c는 %s이다.", a,b,c); 
		
		
	}
}
