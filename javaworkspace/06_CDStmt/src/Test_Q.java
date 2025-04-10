import java.util.Scanner;

public class Test_Q {
	public static void main(String[] args) {
		// 문제 만들기
		Scanner sc = new Scanner(System.in);
		
		// if문 
		System.out.println("어려워요?");
		System.out.println("가. 그렇다 \t  나. 아니다");
		
		// 가 - 조금 더 힘내세요~ ^^
		// 나 - 좋아요~ 
		
		String answer = sc.next();
		
			if (answer.equals("가")) {
				System.out.println("조금 더 힘내세요~^^");
			} else if(answer.equals("나")) {
				System.out.println("좋아요~");	
			}
			
		System.out.println("=============");
		
		// switch
		System.out.println("재밌나요?");
		System.out.println("1. 그렇다 \t  2. 아니다");
		// 1 - good~ ^^
		// 2 - ㅜ.ㅜ

		int answer2 = sc.nextInt();
		
		switch (answer2) {
		case 1:
			System.out.println("good~ ^^");
			break;
		case 2:
			System.out.println("ㅜ.ㅜ");
			break;
		default:
			break;
		}
	}

		
	}
