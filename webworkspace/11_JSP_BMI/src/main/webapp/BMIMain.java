import java.util.Scanner;

public class BMIMain {
	public static void main(String[] args) throws InterruptedException {
		// BMI (체질량 지수 구하는 프로그램)

		// 1. 필요한 값 		키, 체중, 이름
		// 2. 계산식	 bmi = 체중/ 신장 * 신장) m 단위
		// 3. 판정
		// 4. 결과출력  소수점은 1~2자리까지만 출력
		// BMI 지수 : 00 
		// 00님, 당신은 00입니다.
		
		System.out.println("키를 입력하세요.");
		Scanner sc = new Scanner(System.in);
		double height = sc.nextDouble();

		System.out.println("체중를 입력하세요.");
		Scanner sc2 = new Scanner(System.in);
		double kg = sc.nextDouble();
		
		System.out.println("이름를 입력하세요.");
		
		Scanner sc3 = new Scanner(System.in);
		String name = sc.next();
		
		
		//★★★★ 여기 물어보기
		if (height > 10){
			//cm로 입력했을 것
			height /= 100;	
		} double bmi1 = (kg / (height*height));

		System.out.print("계산중.");
		Thread.sleep(300);
		System.out.print(".");
		Thread.sleep(300);
		System.out.print(".");
		Thread.sleep(300);
		System.out.print(".");
		Thread.sleep(300);
		System.out.print(".");
		Thread.sleep(300);
		System.out.print(".");
		Thread.sleep(300);
		System.out.print(".\n\n");

		System.out.printf("BMI 지수 : %.1f\n",bmi1);
		// printf > 자료형 변환  // f > 소수점 // d > 정수
		
		if(bmi1 < 18.5) {
			
			
			System.out.println(name + "님, 당신은 저체중입니다.");			
		} else if (bmi1 >= 18.5 && bmi1 <=22.9) {
			System.out.println(name + "님, 당신은 정상입니다.");			
		} else if (bmi1 >= 23.0 && bmi1 <=24.9) {
			System.out.println(name + "님, 당신은 비만전단계 입니다.");			
		} else if (bmi1 >= 25.0 && bmi1 <=29.9) {
			System.out.println(name + "님, 당신은 1단계 비만입니다.");			
		} else if (bmi1 >= 30.0 && bmi1 <=34.9) {
			System.out.println(name + "님, 당신은 2단계 비만입니다.");			
		} else if (bmi1 >= 35.0) {
			System.out.println(name + "님, 당신은 3단계 비만입니다.");			
		}
		System.out.println("종료 하려면 아무키나 누르세요.");
		sc.next();
		
			
		}
	}
		


/* 
1. import 하기 (프로젝트 가져오기)
file > import > general > existing Projects into workspace

2. 소량의 파일만 가져가거나 단순히 볼때
workspace > project > src > .java파일(메모장열림)
		           또는 이클립스 프로젝트에 복사
		           
3. jar파일 배포하기
file > export > java > runnable jar file > *배포 프로젝트 선택*

4. bat 파일 만들기 (jar파일 실행)
메모장을 열고 아래의 명령어를 작성한 후
'다른이름으로 저장'  에서 파일형식을 '모든파일'로 선택 후
파일명.bat로 저장
							cmd에다가 치면 되는 명령어
	- 경로가 같이 있는 경우
		java -jar 파일명.jar
	- 경로가 다른경우 jar파일의 경로를 기입
	*/	
		
		
	