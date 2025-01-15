import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		// 결과 출력 
			// 5 + 2 + 6 + 14 = 27
			// 총합 : 27
		
		Scanner sc = new Scanner(System.in);
		System.out.println("입력 1: ");
		int a = sc.nextInt();
		System.out.println("입력 2: ");
		int b = sc.nextInt();
		System.out.println("입력 3: ");
		int c = sc.nextInt();
		System.out.println("입력 4: ");
		int d = sc.nextInt();
		
		int tt = a+b+c+d; // (괄호 계산으로도 가능)
		System.out.printf("%d + %d + %d + %d = %d\n",a,b,c,d,tt);
		System.out.printf("총합 : %d",tt);
		
		//또는 *** System.out.println(a + " + " + b + " + " + c + " + " + d + " = " + tt);
		//또는 *** System.out.println("총합 : " + tt);
	}
}


