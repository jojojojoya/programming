import java.util.Scanner;

public class FMain02 {
	
	// 월급을 넣으면 연봉을 구해서 출력해주는 함수 만들기
	
	
	
	public static void getSalary(int month) {
		System.out.println("연봉 : " + month * 12);
	}
	
	// 월급을 넣으면 연봉을 "구해주는" 함수
	public static int getSalary2(int month) {
		return month * 12; //int랑 return은 세트임 
	}	
	
	

	
	// 상품 가격을 넣으면 부가세를 구해주는 메서드
	public static int getVat2(int price) {
		return price / 10;
	}
	
	// 상품 가격을 넣으면 부가세를 출력해주는 메서드 

	
	public static void getVat(int price) {
		System.out.println("부가세 : " + price / 10);
	}
	
	// 숫자 2개를 넣으면 더 큰 수를 구해주는 메서드 

	public static int getBigger(int a, int b) { /*둘중 하나인 케이스 :>> 삼항연산자*/ 
		return a > b ? a : b;
	}		
/*		if (a > b) {
			return a;
		}else 
		return b; // else를 굳이 안써도 됨
	}
*/
	
	
	// TEST (위에 최대한 참고하지 말고)
	// 메서드 (가능) 만들고 main에서 호출해서 사용까지
	
	
	// 3. 숫자 2개를 넣으면 더 큰 수를 구해주는 함수 
	// 비교할 숫자 1,2를 각각 입력받아 처리
	
	public static int getBigger2(int a, int b) {
		return a> b ? a : b;
	}
	
	// 1. 중간, 기말 점수를 입력하면 평균 점수를 구해주는 함수
	// 	Scanner로 중간 기말 입력받아 처리 
	public static int avg(int ki, int jo) {
		int ttl = (ki + jo) /2;
		return ttl;
	}
	
	// 2. 나이를 넣으면 인사말을 출력해주는 함수 (scan 입력받아 처리)
	// 10대 : 안녕
	// 20대 : 안녕하세요
	// 30대 : 안녕하십니까!
	
	public static void bb(int a) {
		System.out.println("나이는? : " + a);
		if (a >= 30)  {
			System.out.println("안녕하십니까!");
		} else if (a >= 20) {
			System.out.println("안녕하세요");
	}	else if (a >= 10){ 
		System.out.println("안녕");
	}}
	
		
		
	public static void main(String[] args) {
	getSalary(200) /* = int month */ ;
	System.out.println(getSalary2(400));
	int aa = getSalary2(250);
	System.out.println(getVat2(1000));
	getVat(10000);
	System.out.println(getVat2(1000));
	
	
	System.out.println("========");
	Scanner sc = new Scanner(System.in);
	System.out.println("비교할 숫자 1 : ");
	int x = sc.nextInt();
	System.out.println("비교할 숫자 2 : ");
	int z = sc.nextInt();
	System.out.println(getBigger2(x, z));

	System.out.println("========");
	System.out.println("기말점수 : ");
	int k= sc.nextInt();
	System.out.println("중간점수 : ");
	int j= sc.nextInt();
	int aa1 = avg(k,j);
	System.out.println(aa1);
	
	System.out.println("나이는? : ");
	int age = sc.nextInt();
	bb(age) ;
	}
	
	}

