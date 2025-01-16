
public abstract class Hero { 
	
	//  abstract를 붙이면 추상클래스가 된다.
	// 1. 추상 클래스는 인스턴스화(객체화) 할 수 없다.(개념 자체가 추상적이니까)
	// 2. 추상 메서드를 하나라도 가지고 있는 클래스는 추상 클래스여야함.
	String name;
	
	
	public Hero() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Hero(String name) {
		super();
		this.name = name;
	}


	// 강제성, 규격이 용도로 사용 
	abstract void attack(); {
		
	}
	
	
}


class SuperMan extends Hero{

	@Override
	void attack() {
		System.out.println("눈알 레이저빔 공격");
		
	}
	
}

class IronMan extends Hero{

	@Override
	void attack() {
		System.out.println("나노 어택");
	}
	
}

class Hulk extends Hero {
	@Override
	void attack() {
		System.out.println("다 때려 뿌수기");
	}
	
}