import java.util.Scanner;

public class IMain3 {
	public static void main(String[] args) {

		// 하나의 인터페이스를 사용했기 때문에 똑같은 기능을 사용할 수 있지만, 기능에 따라 다른 결과가 나옴
			// 상위타입에 하위 타입을 담음으로서 구현이 가능 >> 업캐스팅
			// 상위타입이 존재할 수 있는 이유 >> 커피, 추상화
		// 정확하게 규격화 하기 위해서는 인터페이스를 사용
		// 조금 더 자율성을 부여하기 위해서는 추상클래스
		
		
		
		
		// 콘솔 안내
		
		// 주문하시겠어요??
		
		// 1. 에스프레소  2. 카페라떼  3. 바닐라라떼  4. 프라푸치노
		// 5. 돈이 모자라요.. -> 그럼 나가 (break;)
		// 그 외 번호 입력시 -> 메뉴에 없네요 
		
		// 2,3 선택시 -> hot or iced 물어보기
		// 나머지 메뉴 선택시 메뉴명,가격만 출력해주기.
		
		// sca, wh, if, 예외처리
		
		
/*		int pick = 0;
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("주문하시겠어요?");
				System.out.println("1. 에스프레소  2. 카페라떼\n3. 바닐라라떼  4. 프라푸치노\n5. 돈이 모자라요..");
				pick = sc.nextInt();

				System.out.println();

				if (pick < 1 || pick > 5) {
					System.out.println("메뉴에 없네요.");
					continue;
				}
				
				
				Coffee coffee = null;
				
				if (pick == 1) {
					Espresso coffee = new Espresso();
				} else if (pick == 2) {
					CafeLatte coffee = new CafeLatte();
				} else if (pick == 3) {
					VanilaLatte coffee = new VanilaLatte();
				} else if (pick == 4) {
					Frappuccino coffee = new Frappuccino();
				} else if (pick == 5) {
					System.out.println("그럼 나가");
					break;
				}
				coffee.name();
				coffee.price();
				coffee.hotOriced();
				System.out.println();
				
				
				
				
			} catch (Exception e) {
				sc.next();
				System.err.println("문자입력오류");
			}
		}

		*/
		
		
	}
}