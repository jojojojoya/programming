import java.util.Random;
import java.util.Scanner;

public class Test_Ngame {
public static void main(String[] args) {
	// number game  (Up Down)
	// 입력, 랜덤 
	
	Random r = new Random();
	Scanner sc = new Scanner(System.in);
	
	// 컴터 숫자 뽑기
	int comNum = r.nextInt(100)+1; // 안에 들어가는 숫자가 한도라고 생각하면 됨
	System.out.println(comNum); // 개발자 확인용
	
	// 유저 입력 받기 
	System.out.println("Num(1~100) : ");
	int ipNum = sc.nextInt();
	
	// 판정
		// 결과에 따라 "정답", "업","다운"
	
	if (ipNum == comNum) {
		System.out.println("정답");	
	} else if (ipNum < comNum && ipNum <= 100) {
		System.out.println("업");
	} else if(ipNum > comNum && ipNum <= 100) {
		System.out.println("다운");
		
	}
	
}
}
