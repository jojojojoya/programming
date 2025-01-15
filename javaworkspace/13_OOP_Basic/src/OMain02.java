
public class OMain02 {
	public static void main(String[] args) {
		// 사람
			// 속성 : 이름, 나이 
			// 자기소개 기능

			// 동혁, 28살 만들고 자기소개
			// 유진, 25살 만들고 자기소개
		
		
		Jiki d = new Jiki();
		d.name = "동혁";
		d.age = 28;
		d.printInfo();
		
		
		System.out.println(d); //주소값
		Jiki y = d;
		System.out.println(y); //주소값
		
		
		y.name = "유진";
		y.printInfo();
		System.out.println("========");
		d.printInfo();
		y = null; // 참조형 변수속에 있는 주소값 지움
		System.out.println(y);
		y.printInfo();
		d = null;
		// GC (Heap 영역 자동 정리, 지금 코드에선 이때 발동
		// 더 이상 그 주소값을 가리키는 변수가 없을때 (사용 안할때)
		
		
		
		// Jiki y = new Jiki();
		// y.name = "유진";
		// y.age = 25;
		// y.printInfo();
		
		
		
		
}
}
