import java.util.Scanner;

public class Test_ChangeMoney {
	public static void main(String[] args) {
	// 잔돈 구해주는 프로그램 
		Scanner sc = new Scanner(System.in);
		//이렇게 입력하면
		// 137894
		// 5만 : 2장
		// 1만 : 3장
		// 5천 : 1장 
		// 1천 : 2장
		// 500원 : 1개
		// 100원 : 3개
		// 50원 : 1개
		// 10원 : 4개
		// 잔돈 계산 불가 금액 : 4원
		
		int money = sc.nextInt();
		
		if (money >= 50000) {
			int fwwww = money / 50000;
			System.out.printf("5만원 : %d장/n",fwwww);
			money -= 50000 * fwwww;
			// money %= 50000; >> money = money % 50000
		} if (money >= 10000) {
				int owwww = money / 10000;
				System.out.printf("1만원 : %d장/n",owwww);
				money -= 10000 * owwww;
		} if (money >= 5000) {
			int twwww = money / 5000;
			System.out.printf("5천원 : %d장/n",twwww);
			money -= 5000 * twwww;
		} if (money >= 1000) {
			int swwww = money / 1000;
			System.out.printf("1천원 : %d장/n",swwww);
			money -= 1000 * swwww;
		} if (money >= 500) {
			int kwwww = money / 500;
			System.out.printf("500원 : %d개/n",kwwww);
			money -= 500 * kwwww;
		} if (money >= 100) {
			int bwwww = money / 100;
			System.out.printf("100원 : %d개/n",bwwww);
			money -= 100 * bwwww;
		} if (money >= 50) {
			int qwwww = money / 50;
			System.out.printf("50원 : %d개/n",qwwww);
			money -= 50 * qwwww;
		} if (money >= 10) {
			int awwww = money / 10;
			System.out.printf("10원 : %d개/n",awwww);
			money -= 10 * awwww;

}
	}
		}
/* 
System.out.println("1만 : " + b + "장");
System.out.println("5천 : " + c + "장");
System.out.println("1천 : " + d + "장");
System.out.println("500원 : " + e + "개");
System.out.println("100원 : " + f + "개");
System.out.println("50원 : " + g + "개");
System.out.println("10원 : " + h + "개");
System.out.println("잔돈 계산 불가 금액 : " + i + "원"); */