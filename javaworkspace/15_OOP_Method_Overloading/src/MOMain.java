import javax.annotation.processing.SupportedSourceVersion;

public class MOMain {
	public static void main(String[] args) {
		
		// calc
		
		// 정수 2개를 넣으면 합을 출력해주는 메서드
		Calc.add(1, 2);
		
		// 정수 3개를 넣으면 합을 출력해주는 메서드
		Calc.add(1, 2, 3);
		// 정수 4개를 넣으면 합을 출력해주는 메서드
		Calc.add(1, 2, 3,4);

		// 실수 2개를 넣으면 합을 출력해주는 메서드
		Calc.add(1.1, 2.1);
		
		// 실수 3개를 넣으면 합을 출력해주는 메서드
		Calc.add(1.2, 2.2, 3.2);
		
		// 5,6,7,8,9,10,11,12,13,....
		Calc.add_free(1,2,3);
		int[] aa = new int[2];
		aa[0] = 1;
		System.out.println(aa[0]);
		
		
		// 다 곱해주는 기능 
		
		Calc.doub(1,2,3,4);
		
		
		
	}
}
