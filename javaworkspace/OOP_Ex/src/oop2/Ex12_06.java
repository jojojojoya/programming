package oop2;

class Car06{
	int speed;
	void upSpeed(int speed) {
		this.speed = speed;
		System.out.println("현재 속도 (슈클) : " + this.speed);
	}
}



class Sedan06 extends Car06{
	
	
@Override
void upSpeed(int speed) {
	this.speed += speed;
	if(this.speed > 200)
	{ this.speed = 200;
		System.out.println("현재 속도 (슈클) : " + this.speed);
	}
}
	}

class Truck06 extends Car06{

public class Ex12_06 {
public static void main(String[] args) {
	
	
	Truck06 t = new Truck06();
	Sedan06 s = new Sedan06();
	
	t.upSpeed(300);
	s.upSpeed(300);
}
}}
