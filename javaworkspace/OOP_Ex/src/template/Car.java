package template;

public abstract class Car {
		public abstract void drive();
		public abstract void stop();
		
		
		public void starCar() {
			System.out.println("시동을 켭니다.");
		}
		public void turnOff() {
			System.out.println("시동을 끕니다.");
		
}
		// 파이널을 붙여서 수정하지 말라고 만들어놓은 메서드 >> 템플릿 메서드
		public void run() { // 명령 시행
			starCar();
			drive();
			stop();
			turnOff();
			
		}}
