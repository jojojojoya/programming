import java.util.Random;
import java.util.Scanner;

public class Test_OE2 {
public static void main(String[] args) {
	// 입력
	Scanner sc = new Scanner(System.in);
	// 랜덤 숫자 뽑을 준비
	
	Random r = new Random();

	int comNum = 0;
	int ans = 0;
	
	while (true) {
	comNum = r.nextInt(10) +1; //0,1,2 3개까지만 나옴
	System.out.println(comNum); // 컴터는 2진수 좋아함

	System.out.println("1. 홀? 2. 짝? 10. 종료");
	ans = sc.nextInt();

	// 예외처리  (1,2,10 만을 허용하고 나머지는 입력 오류라고 안내 필요)
	
	if (ans == 10) {
		System.out.println("게임 종료");
		break;
		
	}else if (ans != 1 && ans != 2) {
		System.out.println("입력 오류");
		continue; // 다음 위의 반복으로 넘어감 
		
	}
	if (comNum % 2 == ans % 2) {
		System.out.println("정답");
	}
	else { 
		System.out.println("땡");
	}	

		
		
		
}

}}
