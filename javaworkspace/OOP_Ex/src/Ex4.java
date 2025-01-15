class Car04{
		private String color;
		private int speed; 
		
		public void upSpeed(int value) {
			speed += value;
		}
		public void downSpeed(int value) {
			speed -= value;

			// public 없을때 디폴트 접근 제어자라고 하는데, 퍼블릭이 없으면 같은 패키지 안에서 밖에 못씀
			
		}
		public void setColor(String color) {
			this.color = color;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
		public String getColor() { //간접겁근한 코드 
			return color;
		}
		public int getSpeed() {
			return speed;
		}
}


	
public class Ex4 { // 파일 이름인 것만 Public을 가져야함
	public static void main(String[] args) {
		Car04 myCar = new Car04();
		myCar.setColor("빨강");
		System.out.println(myCar);

		
	}
	
}
