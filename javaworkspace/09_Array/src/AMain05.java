
public class AMain05 {
	public static void main(String[] args) {
		// 2차원 배열
		// 학생들의 키, 체중 (한명만 만들게 아님)
		// int [][] >>>> 2차원 배열은 이 대괄호를 한번만 더 써주면 됨
		// int [행][열]

		int studs[][] = {

				{ 180, 80 }, { 170, 70 }, { 160, 60 }

		};

		// 첫번째 학생의 키
		System.out.println(studs[0][0]); // 행,열의 위치

		// 세번째 학생의 체중
		System.out.println(studs[2][1]); // 행,열의 위치

		// 두번째 학생의 키
		System.out.println(studs[1][0]); // 행,열의 위치

		// 이름, 사는곳
		String[][] ss = { { "진현", "종각" }, { "도윤", "인천" }, { "지원", "동대문" }

		};
		// 두번째 사람의 사는곳
		// 첫번째 사람의 이름
		// 세번째 사람의 이름과 사는 곳 00/00 형태

		System.out.println(ss[1][1]);
		System.out.println(ss[0][0]);
		System.out.printf(ss[2][0] + "/" + ss[2][1]);

		System.out.println("\n==========================");

		int[][] ar = new int[3][4];
		// 값 세팅
		int num = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				ar[i][j] = num++;
				System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}
		// 값 출력
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				ar[i][j] = num++;
				System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("=======================");
		// [면], [행], [열]
		int[][][] aa2 = { { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }
				}, { { 10, 11, 12 }, { 13, 14, 15 }, { 16, 17, 18 } },
				{ { 19, 20, 21 }, { 22, 23, 24 }, { 25, 26, 27 } }, };
		// 10
		System.out.println(aa2[1][0][0]);
		// 4
		System.out.println(aa2[0][1][0]);
		// 17
		System.out.println(aa2[1][2][1]);
		// 23
		System.out.println(aa2[2][1][1]);
		// 26
		System.out.println(aa2[2][2][1]);
		
		
			// 아래처럼 출력해야함
				for (int i = 0; i < 3; i++) { // 면 
					for (int j = 0; j < 3; j++) { // 행
						for (int j2 = 0; j2 < 3; j2++) { //열
							System.out.printf(aa2[i][j][j2] + " ");
						}
						System.out.println();
					}			
					System.out.println();
			// 3차원까지는 자주 쓰니까 이해 잘해야하긔 >> 리온한ㅌㅔ 설명해달라고 하기
			}
			
				
				int [][][][] test = new int [2][2][2][2];
		}
	}