class Car05{
		private int speed; 
		
		public void upSpeed(int value) {
			speed += value;
		}
		public void downSpeed(int value) {
			speed -= value;

			// public 없을때 디폴트 접근 제어자라고 하는데, 퍼블릭이 없으면 같은 패키지 안에서 밖에 못씀
			
}


	
public class Ex5 { // 파일 이름인 것만 Public을 가져야함
	public static void main(String[] args) {
		Car05 myCar = new Car05
				();
		myCar.upSpeed(10000);
		// mycar.speed에 접근해서 직접 접근으로 값을 변경할 수 있기 때문에 private를 사용
		// 간접적인 접근에서는 제어할 수 있기 때문에, 
		
	}}}
	
