class Car06{
		private int speed; 
		private String color; 
		
public Car06() { 
	color = "빨강";
	speed = 0;
}

	
public int getSpeed() {
			return speed;
		}


		public String getColor() {
			return color;
		}

		}




public class Ex6 { // 파일 이름인 것만 Public을 가져야함
	public static void main(String[] args) {
		Car06 myCar = new Car06();
		Car06 myCar2 = new Car06();
		System.out.println(myCar.getColor());
		System.out.println(myCar2.getColor());
		
	}}
	








