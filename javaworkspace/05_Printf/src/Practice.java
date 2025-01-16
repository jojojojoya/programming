import java.util.Scanner;

public class Practice {
	public static void main(String[] args) {
		
		
		// 값을 입력받아서 처리 
		// 입력받을 준비 >>>> ★★★★★★★ 여기 복습 
			// 최상단에 임포트 쳐야함 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("이름을 입력하세요");
		String name = sc.next();
		System.out.println("나이를 입력하세요");
		int age = sc.nextInt();
			// 
		System.out.println("사는곳을 입력하세요");
		String area = sc.next();
		
		System.out.println("*****************");
		System.out.println("*이름\t:" + name + "\t*");
			// 또는 System.out.printf("*이름\t:  %s \t*",name); 
		System.out.println("*나이\t:" + age + "\t*");
			// 또는 System.out.printf("\n*나이\t:  %d \t*",age); 
		System.out.println("*사는곳\t:" + area + "\t*");
			// 또는 System.out.printf("\n*사는곳\t:  %s \t*",area); 
		System.out.println("*****************");
		
		
	}
}
