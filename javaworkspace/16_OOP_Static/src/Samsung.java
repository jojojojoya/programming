
public class Samsung {

		private String name; // 멤버변수, 인스턴스 변수 >> 객체화를 시켜야 쓸 수 있으니까 // 객체가 있어야 메모리가 생기고 주소가 생긴다.   
		private int price; // 
		private static final String MAKER = "SAMSUNG";
		private static int cnt; // static 변수. 클래스 변수
		
		public Samsung() {};

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static int getCnt() {
			return cnt;
		}

		public static void setCnt(int cnt) {
			Samsung.cnt = cnt;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public static String getMaker() {
			return MAKER;
		}

public Samsung(String name, int price) {
			super();
			this.name = name;
			this.price = price;
			cnt++; //객체가 생성될때 카운트 값을 올린다
		}

//		void getName(String name) {
//		
//			this.name = name;
//		}
//
//		String setName() {
//			return name;
//		} 
//			
//		void getPrice(int price) {　// 값을 세팅하는 존재 setter 
//		
//			this.price = price;
//		}
//		
//		int setPrice() { // 값을 얻는 존재 getter  
//			return price;
//		} 
		
		
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public int getPrice() {
//			return price;
//		}
//
//		public void setPrice(int price) {
//			this.price = price;
//		}
//
//		public static String getMaker() {
//			return MAKER;
//		}
//
//		public Samsung(String name, int price) {
//			super();
//			this.name = name;
//			this.price = price;
//		}
//		
		public void printInfo() {
			System.out.println(name);
			System.out.println(price);
			System.out.println(MAKER);
		}

}
