import java.util.Random;
import java.util.Scanner;

public class Test_OE {
public static void main(String[] args) {
	// 입력
	Scanner sc = new Scanner(System.in);
	// 랜덤 숫자 뽑을 준비
	Random r = new Random();
	
	int comNum = r.nextInt(3); //0,1,2 3개까지만 나옴
	System.out.println(comNum); // 컴터는 2진수 좋아함

	// 사용자가 숫자 입력하기
	System.out.println("1. 홀? 2. 짝?");
	int ans = sc.nextInt();

	
	// ★★★★아래처럼 가능 
	
//	if (comNum % 2 == ans % 2) {
//		System.out.println("정답");
//	} else { System.out.println("땡");	
//	}
	
	
	// 판정
	if (comNum % 2 == 1 && ans % 2 ==1) {
		System.out.println("정답");
	}else if (comNum % 2 == 1 && ans % 2 ==0) {
		System.out.println("땡");
	}
	else if (comNum % 2 == 0 && ans % 2 ==0) {
		System.out.println("정답");
	}
	else if (comNum % 2 == 0 && ans % 2 ==1) {
		System.out.println("땡");
		
		// 판정
        // 맞히면 "정답"
        // 틀리면 "땡"

        // 내가 홀인데 컴터도 홀이면 -> 정답
        // 내가 홀인데 컴터가 짝이면 -> 땡
        // 내가 짝인데 컴터도 짝이면 -> 정답
        // 내가 짝인데 컴터도 홀이면 -> 땡

        // 결과출력
		
	
		
		
		
		
		
}

}}
