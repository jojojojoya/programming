package template;

import javax.crypto.Mac;

public class CarTest {
	public static void main(String[] args) {
		System.out.println("자율주행 자동차 -------");
		
		Car myCar = new AICar(); //  업캐스팅
		myCar.run();
		
		
		System.out.println("\n");
		System.out.println("사람이 운전하는 자동차 -------");
		
		Car maCar = new ManualCar();
		maCar.run();
		
		
		
		
		
	}
	
}
