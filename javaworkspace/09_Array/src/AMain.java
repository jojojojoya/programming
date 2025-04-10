import java.util.Scanner;

public class AMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 조건문은 필수적. 반복문은 없어도 됨. 배열 
		
		// int a,b,c,d.a..a..a..a..zz; a1, a2, b2 변수 그릇을 만들건데 원하는 갯
		
		
		int score[] = new int[4]; //4칸짜리 박스가 만들어진 것

			System.out.print("입력할 값 1: ");
			score[0] = sc.nextInt();
			// 0번째 박스에 숫자를 입력해라 
			System.out.print("입력할 값 2: ");
			score[1] = sc.nextInt();
			
			System.out.print("입력할 값 3: ");
			score[2] = sc.nextInt();
			
			System.out.print("입력할 값 4: ");
			score[3] = sc.nextInt();
			
			int result = score[0] +score[1] +score[2] + score[3];
			System.out.println("총합 : " + result);
		
			
			
			
	}
}
