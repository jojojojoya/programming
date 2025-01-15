
public class RS_Practice02 {
	public static void main(String[] args) {
		// 2중 for문
		// 1~12월까지 다 출력 ex) 1월 1일 ~ 12월 31일
		// 월당 일수에 대해서

		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < 32; j++) {
				switch (i) {
				case 2:
					System.out.printf("%d월 %d일\n", i, j);

					if (j > 28)
						break;

				case 1, 3, 5, 7, 8, 10, 12:
					System.out.printf("%d월 %d일\n", i, j);
					break;

				case 4, 6, 9, 11:
					System.out.printf("%d월 %d일\n", i, j);
					break;

				default:
					break;
				}
			}
		}

		// j가 2면 28일까지를 출력

//// 
		int dayMax = 0;
		for (int month = 1; month < 13; month++) {
			switch (month) {
			case 4, 6, 9, 11:
				dayMax = 30;
				break;
			case 2:
				dayMax = 28;
				break;
			default:
				dayMax = 31;
				break;
			}
			for (int day = 1; day <= dayMax; day++) {
				System.out.printf("%d월 %d일\n", month, day);

			}
		}

		int dayMaxt = 0;
		for (int month = 1; month < 13; month++) {
			switch (month) {
			case 4, 6, 9, 11:
				dayMaxt = 30;
				break;
			case 2:
				dayMaxt = 28;
				break;
			default:
				dayMaxt = 31; // dayMaxt > 위에 int로 넣어줘도됨
				break;
			}
			for (int day = 1; day <= dayMaxt; day++) {
				System.out.printf("%d월 %d일\t", month, day);
				// 똑같이 생긴 애가 있으면 중복 제거할 수 있는 부분 있을지 확인해본다

			}
		}

		for (int month = 1; month < 13; month++) {
			for (int day = 1; day < 32; day++) {
				if (month == 2 && day == 29) {
					break;
				} else if (month == 4 || month == 6 || month == 9 || month == 11 && day == 31) {
					break;
				}
				System.out.printf("%d월 %02d일\n", month, day);
			}
			System.out.println();
		}

		for (int day = 1; day <= 31; day++) {
			for (int month = 1; month <= 12; month++) {
				switch (month) {
				case 2:
					if (day > 28) {
						System.out.printf("\t\t|");
						continue;
					}
					break;
				case 4, 6, 9, 11:
					if (day > 30) {
						System.out.printf("\t\t|");
						continue;
					}
					break;

				default:
					break;
				}
				System.out.printf("\t%02d월%02d일 |", month, day);
			}
			System.out.println("");

		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	// 행 12개인데, 고정값이 1, 월데이터만 변동이라는 것을 인지
		// 

	for (int i = 1; i <= 31; i++) {
	for (int j = 1; j <= 12; j++) {
			if (j == 2 &&  i >= 29 || 
					((j == 4 || j == 6 || j == 9 || j == 11) && i >= 31)) {
					System.out.printf("\t\t");
				continue;
			}
			System.out.printf("%02d월 %02d일 ||\t",j,i);
		}
		System.out.println();
	}
			
	System.out.println("");
	}}	


// 가로 출력
