
public class RSMain02 {
	public static void main(String[] args) {
		// 무한루프
		
//		for (int i = 0; true /*조건식*/; i++) {
//			System.out.println(i);
//		}
//		for (;;) {
//			System.out.println("test");
//			
//		}
//		for (int i = 0; i < 3;) {
//			System.out.println(i);
//			i++; 
//		}
//		while (true) {
//			System.out.println("while test");
			
		int q = 0;
		for (;;) {
			System.out.println("for 문 무한루프 : " + q);
			q++;
			if (q == 3) {
			break; // for 문 탈출
			
			}
		}
		int x = 0;
		while (true) {
			System.out.println("while문 무한루프 : " + x);
			x++;
			if (x == 5) {
				break; //while 탈출
			}
		}
		}
		
		// 반복횟수가 명확하면 for, 명확하지 않으면 while	
/*	int i = 1
	for(;;) {
		
	int j = 0;
	for (;;) {
		System.out.println("for 문 무한루프 : " + q);
		j+2;
		if (j < i) {
		break; // for 문 탈출
		}
		i++;
		if(i<6) {
			break;
		}
		
		}
	}
*/		
	}
