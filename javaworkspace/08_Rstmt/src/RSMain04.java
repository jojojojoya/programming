import java.awt.event.WindowListener;
import java.util.Scanner;

public class RSMain04 {
public static void main(String[] args) {
	// 1+----10 = 55
	
	int a =0;
	for (int i = 0; i < 11 ; i++) {
		a += i;
	}
	System.out.println("총합 : " + a);
// for (반복횟수)
	// while (반복 조건)
	// 1+~ 몇까지 더해야 5000이 넘나요?
	// 10 넘는 ? 1+2+3+4+5...15
	
	int i = 1;
	int b = 0; //1
	while (b <= 5) {
		b += ++i; // 2 //
		System.out.println(b);
		// ++1 > 대입하기 전에 미리 연산, 1++ 대입한 후 연산  
		// 전위, 후위 연산자
		
	}
	System.out.println(i-1+"까지 더해야 10이 넘어요");

	Scanner sc = new Scanner(System.in);
	// 입력 
	// 0을 넣으면 STOP
	int d = 1; // 실행문
	// 조건문을 건다면 초기값을 고려해야 한다 
	while (d != 0) { // 0이면 반복이 되어버리니까 
		System.out.println("d : ");
		d = sc.nextInt();
		
	}
	int e = 0;  // 그릇 하나 만들어서 컴터 입장에서는 돌리는게 편하다
	while (true) {	
		System.out.println("e : ");
		e = sc.nextInt();
		if (e == 0) {
			break;
			// while 사용할 때 if, break 함께 써야 종료됨
		}}
	
		System.out.println("=====================");
		/// aaa에 10이 들어가면 stop (10은 종료기능임을 가정)
		
		int aaa = 0;
		while (true) {
			System.out.println("aaa : ");
			aaa = sc.nextInt();
			if (aaa == 10) {
				break;
				
			}
			
		}
		System.out.println("=====================");

		//최대한 위에 안보고 도전
		// 몇까지 더해야 500이 넘나
		
		
		
		int i2 = 1;
		int total = 0;
		while (true) { 
			total += i2++;
			if (total > 500) {
				break;
			}
			
		}
		System.out.println(i2-1); // 여기 왜빼는거임?? 
	
		}
	

//		int kk = 0;
//		int ll = 0;
		
//		do { // do를 먼저 실행하고 나서 조건을 충족하면 아래를 실행
			
//			kk++;
//			ll+=2;
			
//	} while (kk < -100);

		
	
	
	}







