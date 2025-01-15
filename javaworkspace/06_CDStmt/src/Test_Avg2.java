import java.util.Scanner;

public class Test_Avg2 {
	public static void main(String[] args) {
		// 성적 평균점수와 등급을 알려주는 프로그램 
		// 입력 받기 (중간, 기말) - int 

		// 평균 점수
		// (중간+기말)/2
		
		// 중간점수를 입력하게 > 기말 점수 입력하게 >> 평균점수 나오게 
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("중간고사 점수를 입력하세요.");
		
		int mid = sc.nextInt();
		
		System.out.println("기말고사 점수를 입력하세요.");
		
		int last = sc.nextInt();
		
		int avg = ((last + mid) / 2);
				
		
		// 판정
		// 90점 이상 A
		// 80점 이상 B
		// 70점 이상 C		
		// 60점 이상 D
		// 나머지 F 		
		
		
		// 결과 > 마지막 콘솔창에 출력되는 결과
		// 평점 : 00
		// 등급 : 00
		
		System.out.println("평점 : " + avg);
		String grade = "";
		
		//  지역 변수 : 중괄호 안에 있는 변수는 위치가 다르기 때문에 고유하게 지정 가능  
		if (avg >=90) {
			grade = "A";
		}else if (avg < 90 && avg >= 80) {
			grade = "B";
		}else if (avg < 80 && avg >= 70) {
			grade = "C";
		}else if (avg < 70 && avg >= 60) {
			grade = "D";
		}else {
			grade = "F";
	}
		System.out.println("등급 : " + grade);
}}

