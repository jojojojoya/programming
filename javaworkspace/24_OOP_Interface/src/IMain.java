interface Coffee { // interface에서는 앱스트랙트를 쓰지 않아도 그냥 추상메서드로 인정

	abstract void name(); // 붙여도 되고 안붙여도 됨	
	void price(); 
	default void hotOriced() { //선택적인 것이라서 필요한 애들에게만 넣을 수 있음 
}}

//interface를 상속받을 때는 implements로 함 >> 구현한다
//abstract를 상속받을 때는 extends로 함 

class Espresso implements Coffee{

	@Override
	public void name() {
		System.out.println("에스프레소");
	}

	@Override
	public void price() {
		System.out.println("2000원");
		
	
		
		
	}
	
}
class CafeLatte implements Coffee {

	@Override
	public void name() {
		System.out.println("카페라떼");
		
	}

	@Override
	public void price() {
		System.out.println("3000원");
		
	}
	@Override
	public void hotOriced() {
		System.out.println("hot or iced?");
		Coffee.super.hotOriced();
	}
}


class VanilaLatte implements Coffee {

	@Override
	public void name() {
		System.out.println("바닐라라떼");
		
	}

	@Override
	public void price() {
		System.out.println("3000원");
		
	}
@Override
	public void hotOriced() {
	System.out.println("hot or iced?");
		Coffee.super.hotOriced();
	}	
}

class Frappuccino implements Coffee{

	@Override
	public void name() {
		System.out.println("프라푸치노");
		
	}

	@Override
	public void price() {
		System.out.println("3000원");
		
	}
	
}


public class IMain {
	public static void main(String[] args) {
		// 다형성 (polymorphism) >> 커피라는 상위 개념에 들어갈 수 있는 것
		// 인터페이스 > 뭔가를 연결하는 역할
		
		Coffee coffee = new CafeLatte(); // 아래있는 개념을 위로 올려 배정하는것 > >하위 업캐스팅
		coffee.name();
		coffee.price();
		coffee = new VanilaLatte();
		coffee.name();
		coffee.price();
		System.out.println("===============");
		
		
		CafeLatte cafeLatte = new CafeLatte();
		cafeLatte.name();
		cafeLatte.price();
		cafeLatte.hotOriced();
	
		VanilaLatte vanilaLatte = new VanilaLatte();
		vanilaLatte.name();
		vanilaLatte.price();
		
	}
}

