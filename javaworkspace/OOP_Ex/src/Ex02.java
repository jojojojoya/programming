class Car02{
		String color;
		int speed; 
		
		void upSpeed(int value) {
			speed += value;
		}
		void downSpeed(int value) {
			speed -= value;

			
			
		}
		public String getColor() { //간접겁근한 코드 
			return color;
		}
		public int getSpeed() {
			return speed;
		}
}


	
public class Ex02 { // 파일 이름인 것만 Public을 가져야함
	public static void main(String[] args) {
		Car02 myCar = new Car02();
		myCar.color = "파랑";
		myCar.speed = 0;

		myCar.upSpeed(30);
		System.out.println(myCar.speed);
		System.out.println(myCar.color); // 멤버변수에 직접 접근한 코드
		System.out.println(myCar.getColor());
		
	}
	
}
