package oop2;



class Car02{
	public Car02() {
		System.out.println("슈클 생성자~");
	}
}

class Sedan02 extends Car02 {
	public Sedan02() {
		System.out.println("섭클 생성자~");
	}
	
}

class Truck extends Car02 {
	
}


public class Ex12_01 {
public static void main(String[] args) {
	
	Sedan02 sedan1 = new Sedan02();
	
}
}
