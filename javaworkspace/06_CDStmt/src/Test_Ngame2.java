import java.util.Random;
import java.util.Scanner;

public class Test_Ngame2 {
public static void main(String[] args) {
	// number game  (Up Down)
	// 입력, 랜덤  맞추면 끝나는 게임
	// @ 몇번만에 맞췄는지 ? (맞췄을 때 안내하기) > 10회 횟수출력 필요 
	// @ 예외처리 - ex) 1~100 아닌 숫자는 입력오류 
	// 횟수제한 10회
	
	
	Random r = new Random();
	Scanner sc = new Scanner(System.in);
	
	// 컴터 숫자 뽑기
	int comNum = r.nextInt(100)+1; // 안에 들어가는 숫자가 한도라고 생각하면 됨
	int cnt = 0 ;
	int limit = 10 ;

	
	while (true) {
	System.out.println(comNum); // 개발자 확인용
	
	System.out.println("Num(1~100) : ");
	int ipNum = sc.nextInt();

	if (ipNum < 1 || ipNum > 100) {
		System.err.println("Error");
		continue;
	}
	cnt ++; 
	limit --;
	
	 if (ipNum == comNum) {
		 System.out.printf("정답! %d번만에 맞췄습니다.",cnt);
		break; // 정답일때 탈출
	} else if(limit == 0) { 
		System.out.printf("game over.");
		
	}else if (ipNum > comNum) {
		System.out.println("다운");
	} else {
		System.out.println("업");
	}
	 System.out.printf("you have %d chance\n",limit);
}	
		
	
	
	
	
	
	
	
	}
		
	}


