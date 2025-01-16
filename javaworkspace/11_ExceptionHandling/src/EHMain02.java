import java.util.InputMismatchException;
import java.util.Scanner;

import javax.security.cert.CertificateEncodingException;

public class EHMain02 {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in); // 출력해라
	int x = 0;
	try {
	System.out.println(" x: ");
	x = sc.nextInt();
	System.out.println(" y: ");
	int y = sc.nextInt();
	int[] ar = {10,20};
	System.out.println("배열 몇번?(0,1) : ");
	int i = sc.nextInt(); 
	System.out.println(ar[i]);
	
	}catch (Exception e) {
		System.out.println("오류 발생");
		e.printStackTrace(); // 결론적으로 얘를 넣어라 
	}}}
	
	
	
/*	
		System.out.println(x);
		System.out.println("오케이~ 다 끝!");

		
		
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in); // 출력해라
			int x = 0;
			try {
				System.out.println(" x: ");
				x = sc.nextInt();
				System.out.println(" y: ");
				int y = sc.nextInt();
				
				int[] ar = {10,20};
				System.out.println("배열 몇번?(0,1) : ");
				int i = sc.nextInt(); 
				System.out.println(ar[i]);
				
			} catch (ArithmeticException e) { //exception이 가장 큰 범위의 
				System.out.println("0으로 나눌 수 없어요!");
		e.printStackTrace();// 개발자 확인용
			} catch (ArrayIndexOutOfBoundsException e) { 
				System.out.println("0과 1만 가능해요");
			} catch (InputMismatchException e) { 
				System.out.println("숫자만 입력 가능해요!");
			}
			*/
			
			
	/*		
			System.out.println(x);
			System.out.println("오케이~ 다 끝!");
		// 바깥에서 안에 들여다보려고 하는 것 >> int x를 밖으로 빼야함 
		
		
		
		//		System.out.println(" x: ");
//		int x = sc.nextInt(); //값받아라
//		System.out.println(" y: ");
//		int y = sc.nextInt();
//		try {
//			System.out.println(x/y); // 예외적용할 함수 
//			
//		} catch (Exception e) { 
//			System.out.println("0으로 나눌 수 없어요!");
//			e.printStackTrace();// 개발자 확인용
//			
//		}int[] ar = {10,20};
//		System.out.println("배열 몇번?(0,1) : ");
//		int i = sc.nextInt(); 
//		try {
//			System.out.println(ar[i]);
			
//		} catch (Exception e) {
//			System.out.println("0과 1만 가능해요");
//			e.printStackTrace();
			
	
	
		
		
		
		
}}
	*/
	
	
