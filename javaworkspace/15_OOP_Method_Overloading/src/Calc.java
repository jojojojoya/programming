import java.util.Iterator;

public class Calc {

	public static void add(int a, int b) {
		System.out.println(a + b);
	}
	
	
		public static void add(int a, int b, int c) {
			System.out.println(a + b + c);
	} //add2를 따로 생성할 필요 x > 파라미터의 갯수가 달라졌기 때문에 같은 이름으로 사용할 수 있음
		// == 메소드 오버로딩
		public static void add(int a, int b, int c, int d) {
			System.out.println(a + b + c + d);
		}
			public static void add(double a, double b) {
				System.out.println(a + b);
			}
				public static void add(double a, double b, double c) {
					System.out.println(a + b + c);

				}
				
				
		// 몇개를 넣어 부르든 다 더해주는 기능 
				
		public static void add_free(double...a) {
			System.out.println(a);
			double hap = 0;
			for (double d : a) {
				hap += d;
			}
				System.out.println("총합 : " + hap);
		}
		
		
				public static void doub(double...a) {
					System.out.println(a);
					double gop2 = 1;
					for (double d : a) {
						gop2 *= d;
					}
					System.out.println("총값 : " + gop2);
}}