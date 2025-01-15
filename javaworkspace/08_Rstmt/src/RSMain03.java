
public class RSMain03 {
	public static void main(String[] args) {
		
	// 중첩 for 문 /  이중 for 문 
		// refactor > rename >>> 변수 이름 한번에 변경 가능
	for (int dan = 2; dan < 10; dan++) {
		for (int j = 1; j < 10; j++) {
			System.out.printf("%d x %d = %d\n", dan, j, dan * j);
		}
		System.out.println("--------");
	}	
	// 디버그 실행 
	// f8 > 전체실행
	// f6 > 한단계씩 실행
	// 이중 for문 활용한 출력 > 디버그 모드 
	
	
	//  1. 
	// 	Z
	//  ZZ
	//  ZZZ
	//  ZZZZ
	//  ZZZZZ
	// 겉에 for 문이 행이다 / 아래 for 문은 열이다 e.g. 5행일때 5열
	
	//	for (int i = 1; i < 6; i++) {
	//		for (int j = 0; j < i ; j++) {
	//			System.out.print("z");
	//		}
	//		System.out.println();

			
			// 2. 	
			//	zzzz
			//	zzz
			//	zz
			//	z
			// 겉에 for 문이 행이다 / 아래 for 문은 열이다 e.g. 5행일때 5열
	
	
	for (int i = 1; i < 6; i++) {
        for (int j = 6; j > i; j--) {
            System.out.print("z");
        }
        System.out.println(    );
			
			
			// 3. 	
			//	z
			//	z
			//	z z
			//	z z 
			//	z z z
	}
	System.out.println("=================");
			
	
	for (int i = 1; i < 6; i++) {
		for (int j = 0; j < i ; j+= 2) { // i 1일때 j 0 > i2일때 j2 z 출력
			System.out.print("z ");
		}
		System.out.println(" ");
	}	
		// 
			// 4. 	
			//	z
			//	 z
			//	  z
			//	   z
			//	    z
	
			// 
			
			System.out.println("=================");
			
			for (int i = 1; i < 6; i++) {
			for (int j = 0; j < i ; j++) { // i 1일때 j 0 > i2일때 j2 z 출력
				System.out.print(" ");
				}
			System.out.print("z");
			System.out.println();
							
		}
	
			// 5. 	
			//	z
			//	 z
			//	  z
			//	   z
			//	    z
			
			
			System.out.println("=================");
			
			for (int i = 1; i < 6; i++) {
				for (int j = 0; j < i ; j++) { // i 1일때 j 0 > i2일때 j2 z 출력
					System.out.print(" ");
				}
				System.out.print("z\n");
				System.out.println();
				
			}
			System.out.println("=================");
			
			for (int i = 1; i <= 5; i++) {
				for (int j = 1; j <= i ; j++) { 
					if (j == i ) {
						System.out.print("z\n\n");
				} else {
					
					System.out.println(" ");
				}
				
			}
	} 
			
	}}


