import java.util.Random;
import java.util.Scanner;

public class RSMain4_Test {
	public static void main(String[] args) {
		// 내가 입력한게 컴 숫자랑 일치할때까지 반복 (맞추면 끝)
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		
		
		while (true) {
			int comNum = r.nextInt(4); // 0123
			System.out.println(comNum); //개발자 확인용
			System.out.println("숫자 입력 하세요~(0~3) : ");
			int userNum = sc.nextInt();
			if (userNum == comNum) {
				System.out.println("정답!");			
				break;
			} 
			
		}
		
		// up & down game - 
		// 홀짝 oe game - 턴마다 컴 랜덤숫자를 다시 추출해야 함 
		
	}
}
