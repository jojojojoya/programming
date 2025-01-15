import java.util.Iterator;

public class RSMain {
	public static void main(String[] args) {
		// repaeat stmt 반복문  
		// for(초기화; 조건; 증감) { 반복될 코드 }
		// 반복의 회수가 명확하면 for문	
			// 일반적으로는 for로 사용하는게 편하다 
		
			for (int i = 0; i < 5 ; i++) {
				System.out.println("ㅎㅎ");
			}

		// 반복 회수가 명확하지 않으면 while
			int z = 0;
			while (z < 3) {
				System.out.println("ㅋㅋㅋ");
				z++;
				
			}

		// zzz를 콘솔에 3번 출력하시오.
			for (int i = 0; i < 3 ; i++) {
				System.out.println("zzz");
			}
			// 지역에서 노는 지역변수라 i 같아도 상관없음 
			for (int i = 0; i < -1 ; i++) {
				System.out.println("111");
			
	}
			// 지역에서 노는 지역변수라 i 같아도 상관없음 
			for (int i = 0; i < 2 ; i++) {
				System.out.println("22");
	}
				
			
			// 지역에서 노는 지역변수라 i 같아도 상관없음 
			for (int i = 0; i < 2 ; i+=2) {
				System.out.println("224");
			}
			
			
			System.out.println("======================");
			// 0 2 4 6 *** 복습
			for (int i = 0; i <= 6; i+=2) {
				System.out.print(i + " ");
			} // int > i 읽었다가 > 아래 식 읽고 위 증감으로 감
			
			System.out.println("======================");
			for (int i = 0; i < 7; i++) {
				if (i % 2 ==0) {				
				System.out.print(i + " ");

							}
				
				
				
				
				
				
			}
System.out.println("-----------------------------");

	// 1~10까지 모든 숫자 다 더하기 
	// 1+2+3+4+5+6+7+8+9+10 = 55

	int a = 0;
	for (int i = 1; i < 11; i++) {
		a += i; // a= a+i 
		System.out.println(a);
		
	}
	System.out.println("총합 : " + a);
	//1+2+3+4..392 = ?
	
	int b = 0;
	for (int i = 1; i < 393; i++) {
		b += i; 
		System.out.println(b);
	}
	
	
		// 1~5까지 다 곱하면 몇?
		int c = 1;
		for (int i = 1; i < 6; i++) {
			c *= i; 
		}
		System.out.println("총곱 : " + c);
		System.out.println("-----------------------------");

		// 1~20까지 다 곱하면 몇?
		// int 범위 : 21억으로 범위를 넘어가면  - 계산 됨
		
		long d = 1;
		for (int i = 1; i < 21; i++) {
			d *= i; 
		}
		System.out.println("총곱 : " + d);
		System.out.println("-----------------------------");
		
		// 구구단 출력 
		// 2 x 1 = 2 
		// 2 x 2 = 4
		// 2 x 3 = 6
		// .......
		
		
		for (int i = 1; i < 10; i++) {
			System.out.println("2 x " + i + " = " + (2*i)); 
			System.out.printf("2 x %d = %d\n",i, i*2);
		}
		
		
		// 1. 3단인데 홀수 곱한것만
		// 3 x 1 = 3
		// 3 x 3 = 9
		// 3 x 5 = 15
		// 3 x 7 = 21
		// 3 x 9 = 27
		
		int k = -1;
		for (int i = 1; i < 6; i++) {
			k = k+2 ;
				System.out.println("3 x " + k + " = " + (k*3));
					
				
				// 1. 3단인데 홀수 곱한것만
				// 3 x 1 = 3
				// 3 x 3 = 9
				// 3 x 5 = 15
				// 3 x 7 = 21
				// 3 x 9 = 27
		}
		
	
	for (int i = 1; i < 10; i += 2) {
		System.out.printf("3 x %d = %d\n",i,i*3);
		
		
	}
		
		// 아래 딱 3개만 나오게 
		// 4 x 9 = 36
		// 4 x 6 = 24
		// 4 x 3 = 12
		
		for (int i = 9; i >= 3 ; i -= 3) {
			System.out.printf("4 x %d = %d\n", i,i * 4);
		}
		// for 조건 수정해서 해결할 수 있음.
		
		System.out.println("-----------------------------");
		
		
		
		
	}}















