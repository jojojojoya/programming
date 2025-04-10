import java.util.Random;
import java.util.Scanner;

public class CCC {
	public static void main(String[] args) {
		// 참참참
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		
		String faceTb1[] = {"", "오른쪽", "위", "아래", "가만히"};
		
		int cnt = 0;
		int comFace = 0;	
		int myFace = 0;	
		while (true) {
		comFace = r.nextInt(5) + 1; //1~5
		System.out.println(comFace); // 개발자 확인용
		System.out.println("=====================");
		System.out.println("1. 왼쪽");
		System.out.println("2. 오른쪽");
		System.out.println("3. 위");
		System.out.println("4. 아래");
		System.out.println("5. 가만히");
		System.out.println("=====================");
		System.out.println("어디?? (숫자로 입력)");
		myFace = sc.nextInt();
		
		if (myFace < 1 || myFace > 5) {
			System.err.println("ERROR!");
			continue; // 위로 올라가버림
			
		}
		System.out.println("나 : " +  faceTb1[myFace]);
		System.out.println("컴 : " +  faceTb1[comFace]);
		
		if (myFace == comFace) {
			System.out.println("걸림");
			System.out.println(cnt + "번 버팀");
			break;
		} 
		else {
			System.out.println("피함");
			cnt++;
		}
		// 판정 > 맞추면 걸린 것 (게임 끝내기)
		
		// 결과출력 > 나 : 왼쪽 / 컴 : 위 라는 것 알려주긔  

		// 맞추면 "걸림"
		// 피하면 "피함"
		
		
		// 예외 처리 (입력 오류) 
		// 피하면 여러번 할 수 있게
		// @몇번 버텼는지 
		
			
			
			
			
			
		}
		
		
		
		
	}
}
