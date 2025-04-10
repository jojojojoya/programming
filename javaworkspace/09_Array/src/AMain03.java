import java.util.Scanner;

public class AMain03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] aa = new int[4];
		
		//입력받기 (값 세팅) 
		// 다 더하기
		// 결과 출력
		for (int i = 0; i < aa.length; i++) {
			System.out.printf("입력할 점수 %d : ", i+1);
			aa[i] = sc.nextInt();
				
		}
		
		int aaa = 0;
		for (int i = 0; i < aa.length; i++) {
			aaa += aa[i];
		}
		
		aaa = 0;
		for (int aka : aa) {
			aaa += aka;
		}
		System.out.println(aaa);
		// 결과출력
		// 1+2+3+4 = 10 모든 값 순차적 입력되고 최종값 얼마나 하고 싶음
		System.out.printf("%d + %d + %d + %d = %d",aa[0],aa[1],aa[2],aa[3],aaa);
		
		
		
		
		System.out.println("\n======================");
		int hap3 = 0;
		for (int i = 0; i < aa.length; i++) {
			System.out.printf("입력할 점수 %d : ", i+1);
			aa[i] = sc.nextInt();
			hap3 += aa[i];
		}
		System.out.println(hap3);
	
		
		System.out.println("\n======================");
		
		for (int i = 0; i < aa.length; i++) {
			if (i == aa.length-1) {
				System.out.printf(aa[i] + " = ");
			}else {
				System.out.printf(aa[i] + " + ");
			}}
			System.out.println(hap3);
			System.out.println("\n======================");
		
			for (int i = 0; i < aa.length; i++) {
				System.out.print(aa[i]);
				System.out.print(i == aa.length-1 ? " = " : " + ");
			}
				System.out.println(hap3);
	
				
				
				
	
	}}