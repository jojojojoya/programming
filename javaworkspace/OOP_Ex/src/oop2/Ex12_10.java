package oop2;
	interface Car11{
		void work();
	}
	interface Cannon {
		void fire();
	}
	
	
	
class Tank implements Car11, Cannon{
	@Override
	public void work() {
		System.out.println("탱크가 굴러갑니다");
		
	}
	public void fire() {
		System.out.println("대포발사");
	
	
}

public class Ex12_10 {
	public static void main(String[] args) {
	
		// 다중 상속을 허용하지 않고, 단일 상속만을 허용 
		// 부모클래스 하나밖에 못가지는데, 인터페이스를 통해 다중 상속을 구현 가능
	
		
		Tank tank = new Tank();
		
		
		
		
		
		
}}}
