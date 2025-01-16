package oop2;

interface Car09{
//	static final int CAR_COUNT = 0;
	int CAR_COUNT = 0;
//	 abstract void work();
	void work();
}

//상속이 아니라 인터페이스를 만들때는 implements

class Sedan09 implements Car09{
	
	@Override
	public void work() {
		System.out.println("승용차가 사람 태움");
	}
}


class Truck09 implements Car09{
	@Override
	public void work() {
		System.out.println("짐 실음");
		
	}
}	

public class Ex12_09 {
public static void main(String[] args) {
		
	Sedan09 s = new Sedan09();
}}
