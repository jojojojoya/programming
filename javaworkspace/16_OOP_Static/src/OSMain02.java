
public class OSMain02 {

	public static void main(String[] args) {
		//  애플 쇼핑몰
		//  goods
		
		//  품명 : 아이폰6s, 가격 : 120, 제조사 : 애플
		//  품명 : 아이패드, 가격 : 150, 제조사 : 애플
		//  품명 : 에어팟, 가격 : 120, 제조사 : 애플
		
		// - 제조사는 정해져 있음. > 상수로 메이커 설정해달라 >> static final 0000 ★★ 상수 명은, 꼭 대문자로 지정 
		// - 품명, 가격 등 '직접' 접근 못하게 > private 함수 씌우기 
		
		// 생성된 제품의 가격, 품명 모두 수정 가능 > 간접접근 setxxxx();
		
		
		AppleGoods iPhone = new AppleGoods();
		// iPhone.name = "아이폰6s";
		//iPhone.price = 120;
		iPhone.printInfo();
		System.out.println("=================");
		
		AppleGoods iPad = new AppleGoods("아이패드", 150);
		iPad.printInfo();
		System.out.println(iPad.getPrice());
		iPad.setPrice(80);
		System.out.println("=================");
		
		AppleGoods airPod /*객체*/ = new AppleGoods();
		//airPod.name = "에어팟";
		//airPod.price = 120;
		airPod.setName("에어팟");
		airPod.setPrice(30);
		airPod.printInfo();
		System.out.println("=================");
		
		
		AppleGoods airPod2 = new AppleGoods("에어팟 프로", 30);
		airPod2.setPrice(25);
		airPod2.printInfo();
		
		System.out.println("=================");
		
		System.out.println(iPad.getPrice());
		System.out.println(iPad.getName()); // 간접접근 
		System.out.println("=================");
		System.out.println(iPad.MAKER); // 직접접근 > 호출 > 프라이빗 안씌워서 직접호출 가능
		
		System.out.println(AppleGoods.MAKER /*클래스명.스태틱 메소드*/ );
		
		
		
		
	
	}
}
