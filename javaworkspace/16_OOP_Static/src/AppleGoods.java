
public class AppleGoods {

	// 품명 : 아이폰6s, 가격 : 120, 제조사 : 애플
	// 품명 : 아이패드, 가격 : 150, 제조사 : 애플
	// 품명 : 에어팟, 가격 : 120, 제조사 : 애플

	private String name;
	private int price; // private > 감추는 것.
	static final String MAKER = "apple"; // 상수 고정값은 미리 값을 부여해도 무방. 상수 대문자로 써야함

	void setName(String name) {
		this.name = name;
	}

	String getName() {
		return name; 
	}

	void setPrice(int price) {
		this.price = price;
	}

	int getPrice() {
		return price; // 복습
	}

///*		void settingPrice(int price) {
//				if (price < 0) {
//					this.price = 0; }
//				else if (price > 200)
//				{ return; // 함수를 그냥 종료하는 방식
//				}
//			this.price = price; // 간접접근 
//			
//		}
//		
//		int gettingPrice() {
//			return price; // 복습
//		}
//	*/	

	public AppleGoods() {
	}

	public AppleGoods(String name, int price) {
		super(); // 오버로딩 생성자 >> 객체를 생성할때 값을 초기화하면서 만드려고 생성 //resource -> create 자동변수
		this.name = name;
		this.price = price;
	}

	public void printInfo() {
		System.out.println(name);
		System.out.println(price);
		System.out.println(MAKER);

	}

}
