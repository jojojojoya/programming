
public class Doctor {
	public void act1(Guest g) { 
		// 손님을 맞이하기
		// 손님한테 정보 물어보기
		// 검사하려면 손님 정보 필요
		
		System.out.println("정보좀 주소(이름,키,체중)");
		g.act2(); 	// 게스트가 본인정보 대답
		// double bmi = act3(g); 	// bmi 수치 계산하기
		act4(act3(g),g.name); 	// 판정, 결과 안내 
		
		// 게스트가 본인 정보를 대답
		
		
	}
	private double act3(Guest g) {
		if (g.height > 10) {
			g.height /= 100;

		} double bmi = g.weight / (g.height * g.height); //
			return bmi;
	}

	private void act4(double bmi, String name) {
		// bmi, name 까지 필요한데 
		String status = "3단계 비만";
		if (bmi < 18.5) {
			status = "저체중";
		}
		else if (bmi <= 22.9) {
			status = "정상";
		}
		else if (bmi <= 24.9) {
			status = "비만전 단계";
		}
		else if (bmi <= 29.9) {
			status = "1단계 비만";
		}
		else if (bmi <= 34.9) {
			status = "2단계 비만";
		}
		System.out.printf("BMI 지수 : %.1f\n",bmi);
		System.out.printf("%s님 당신은 %s입니다.",name, status);
	}
}
