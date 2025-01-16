package exam;

interface Soundable {
	String sound();
}

class Cat implements Soundable{
	
	@Override
	public String sound() {
		return "야옹";
	}
	
}
class Dog implements Soundable{
	
	@Override
	public String sound() {
		return "멍";
	}
}

public class SoundableExample {
	public static void printSound(Dog dog) { 
		System.out.println(dog.sound());  // 메소드 오버로딩 
	}
	
		public static void printSound(Cat cat) { 
			System.out.println(cat.sound()); // 메소드 오버로딩 
			// 다형성을 구현할 수 있는 방법 > 상위타입에 하위타입을 넣는다. 
		}

		
		public static void main(String[] args) {
		printSound(new Dog());
		printSound(new Cat());
	}
}