package oop2;

abstract class Car08{
	int speed;

	void upSpeed(int speed) {
		this.speed = speed;
	}
	
	 abstract void work();
}


class Sedan08 extends Car08{
	
	@Override
	void work() {
		System.out.println("승용차가 사람 태움");
	}
}


class Truck08 extends Car08{
	@Override
	void work() {
		System.out.println("짐 실음");
		
	}
}	

public class Ex12_08 {
public static void main(String[] args) {
	
	
}}
