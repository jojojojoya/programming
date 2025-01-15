import java.util.Scanner;


public class IMain02 {
	public static void main(String[] args) {
		
		Espresso espresso = new Espresso();
		CafeLatte caLatte = new CafeLatte();
		VanilaLatte vanilaLatte = new VanilaLatte();
		Frappuccino frappuccino = new Frappuccino();
				
		// 콘솔 안내
		// 주문 하시겠어요??
		// 1. 에스프레소	 2. 카페라떼	 3. 바닐라라떼	 4. 프라푸치노
		// 5. 돈이 모자라요. - > 그럼 나가 (break;)
		// 그 외 번호 입력 시 > 메뉴에 없네요. 
		
		// 결합도가
		
		// 예외처리 
		// 2,3 선택시 -> hot or iced 를 선택할 필요 O 
		// sca, wh, if or swi, 예외처리
		
		
		System.out.println("주문 하시겠어요?");
		System.out.println("1. 에스프레소 2. 카페라떼 3. 바닐라라떼 4. 프라푸치노 5. 돈이 모자라요.");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int a = sc.nextInt();
					
					switch (a) {
					case 1:
						espresso.name();
						espresso.price();
						System.out.println("주문 받았습니다.");
						break;
					case 2:
						caLatte.name();
						caLatte.price();
						caLatte.hotOriced();
						break;
					case 3:
						vanilaLatte.name();
						vanilaLatte.price();
						vanilaLatte.hotOriced();
						break;
					case 4:
						frappuccino.name();
						frappuccino.price();
						System.out.println("주문 받았습니다.");
						break ;
					
					}
					if (a==5) {
						System.out.println("그럼 나가");
						break;
					}
					
					try {
						
					} catch (Exception e) {
						sc.next();
						System.err.println("메뉴에 없어요.");
					}
		}}
	}

