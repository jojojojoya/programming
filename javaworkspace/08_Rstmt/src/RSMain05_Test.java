import java.util.Scanner;

public class RSMain05_Test {
	public static void main(String[] args) {
		// lable x 
		// 1. 상품등록 2. 상품검색 3. 상품삭제 4. 종료
		// 몇번? (사용자가 입력)
		// 1번 입력 시 "등록합니다", 출력
		// 2번 : 검색합니다  / 3번 삭제합니다 / 4번 프로그램 종료 
		// 그 외, 입력오류 출력
		// sca, switch or if (cd문 조건문), (rs문 반복문) 
		
		int aaa = 0;
		System.out.println("1. 상품등록 2. 상품검색 3. 상품삭제 4. 종료");
		Scanner sc = new Scanner(System.in);
		
			
		while (true) {
			aaa = sc.nextInt();
					
					switch (aaa) {
					case 1:
						System.out.println("등록합니다.");
						break;
					case 2:
						System.out.println("검색합니다.");
						break;
					case 3:
						System.out.println("삭제합니다.");
						break;
					case 4:
						System.out.println("프로그램 종료");
						break ;

					default: 
						System.out.println("입력오류");
						
						break;
					}
						if (aaa == 4) {
						break;
						
					}
				
				}
			}

		
		
		
		
	}	
