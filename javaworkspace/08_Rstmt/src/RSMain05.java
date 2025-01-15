import java.util.Scanner;

public class RSMain05 {
	public static void main(String[] args) {
		
		
		// break : 현재 위치에서 가장 가까운 switch문 또는 반복문 탈출

		
		// continue : 현재 반복을 건너뛰고 증감식으로 넘어감 (다음 반복 진행)
		
		
		for (int i = 0; i < 11; i++) {
			if (i % 2 == 1) {
				continue;
			}
			System.out.println(i);
		}
	
		
		Scanner sc = new Scanner(System.in);
		int ans = 0;
		
		//aaa, bbb 라는 labeling
		
		aaa : for (int i = 0; i < 3; i++) {
			
			bbb : while (true) { // dead code = if에서 false라서 뜨는 경우 O
				System.out.println("정답은 ?");
				ans = sc.nextInt();
				
				
				switch (ans/*변수*/) {
				case 1:
					System.out.println("정답");
					break;
				case 2:
					System.out.println("오답");
					break;
				case 3:
					System.out.println("종료");
					// break bbb; // bbb를 사용한 while문 종료
					break aaa; // aaa를 사용한 for문 종료
					

				default:
					break;
				}  
				
				// if(ans ==3)
				// { break;				
				// break : 현재 위치에서 가장 가까운 switch문 또는 반복문 탈출
				// 했을 때 while에서 멈춰야 식이 끝나니까, while에도 브레이크를 걸어줘야 함
				
				
			}
				
			}
			
			
		}
	}	
