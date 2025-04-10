import java.util.Random;
import java.util.Scanner;

import org.w3c.dom.UserDataHandler;

public class RSPMAin {
	public static void main(String[] args) {
		
	//가위바위보

	Scanner sc = new Scanner(System.in);
	Random r = new Random();
	String[] handTb1 = {" ", "가위", "바위", "보"};
	int userHand = 0;
	int comHand = 0; // 안에 들어가는 숫자가 한도라고 생각하면 됨
	int vic = 0;
	int combo = 0;
	double winP = 0;
	int total = 0;
	int max = 0;
	
	
	while (true) {
		total++;
	//고객이 입력, 랜덤값 뽑을 준비
//	comHand = r.nextInt(3)+1;
	comHand =1;
	System.out.println("============");
	System.out.println("1. 가위");
	System.out.println("2. 바위");
	System.out.println("3. 보");
	System.out.println("4. 종료");
	System.out.println("============");
	System.out.println("컴퓨터 낸거?" + handTb1[comHand]); // 개발자 확인용
	System.out.println("뭐 ? ");
	userHand = sc.nextInt();

	
	if (combo > max) {
		max = combo;
	}
	
	if (userHand == 4) {
		System.err.println("게임을 종료합니다.");
		System.out.println("총 이긴 횟수 : " + vic);
		System.out.println("최다 연승 : " + max);
		break;
		}	else if (userHand < 1 || userHand > 4) {	
		System.err.println("error!");
		continue;
		}

	
	
	System.out.println("나 : " +  handTb1[userHand]);
	System.out.println("컴 : " +  handTb1[comHand]);
	

	
	// comHand - userHand =0이면 무승부 
	
	
	int result = comHand - userHand;
	
	
	if (comHand  == userHand) {
		System.out.println("무승부");
	} else if (result == -1 || result == 2 ) { 
		vic++;
		combo++;
		System.out.printf("현재 %d 연승중!\n", combo);
		System.out.println("승"); 
	} else {
		System.out.println("패");
		combo = 0;
	}
	winP = (double)vic / total * 100;
	System.out.println(vic);
	System.out.println(total);
	System.out.printf("현재 나의 승률 : %.1f%%\n", winP);
	
			
		
			
		}}}

	
	// 입력받기 
	
	//판정 & 결과
	// 나 : 00
	// 컴 : 00
	// 승 / 패 / 무승부
	// @ x 연승중!! 이라는 문구 (연승처리)

		// IF userHand, comHand로 나눠서 승패를 가를 수 있음
		// 승리할 시, 승리횟수를 증가시켜서  출력할 수 있는 ++를 출력
		// comHand - userHand == 0이면 무승부, 승 : -1 or 2 / 패 : -2 or 1
		// int result = comHand - userHand에 부여해서 출력 대체  (user hand < 1 // userhand > 4)

	
// =================================================================================================================
// =================================================================================================================
	
	// 게임반복 >> 반복문
	// 예외처리 	>>> userHand == 4 > break를 만남
	// 몇번 이겼나? - 종료시에만 총 이긴 횟수 안내
	
	
	
// =================================================================================================================
// =================================================================================================================
	
	// @ 연승처리
	// @ 최다 연승 횟수 안내
	// (연승과 최다 연승은 다름. 2번 이겼다가 지면 연승 깨지는 거고 4번 연승한 적 있다가 지고 2번 연승하고 게임을 종료하면 최다 연승은 4)
	// @ 현재 나의 승률 = 이긴거 / 전체게임수 * 100 **(나의 경우엔 비긴거  합산 하지말긔)
	
		// if 승패 판정문에서 승리할때 승리 횟수 ++, 연승횟수 ++, 연승에서 패할 경우의 반복문 else 부분에 0을 세팅
		// 연승 판정 문구의 경우 if 승패 판정문에서 승리 파트에 부여
		
		// total = > 총 횟수 // total-- >> 횟수를 차감
		// 일단 최상단에서 int로 모두 정의하고 시작 
	