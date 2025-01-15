class Car03{
		private String color;
		private int speed; 
		
		void upSpeed(int value) {
			speed += value;
		}
		void downSpeed(int value) {
			speed -= value;

			
			
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


	
public class Ex3 { // 파일 이름인 것만 Public을 가져야함
	public static void main(String[] args) {
		Car03 myCar = new Car03();
		myCar.setColor("빨강");
		System.out.println(myCar);

		
	}
	
}
