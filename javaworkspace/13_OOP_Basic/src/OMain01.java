
public class OMain01 {
public static void main(String[] args) {
		// 객체 지향 - 실생활 반영
		// 객체 - 존재하는 모든 것. 커피도 객체다.

	
		// dog
		// 나이가 3살이고 / 이름이 초코 / 체중이 3.5kg
		
	
		int age = 3;
		String name = "초코";
		double weight = 3.5;
		System.out.println("강아지 나이 : " + age);
		System.out.println("강아지 이름 : " + name);
		System.out.println("강아지 체중 : " + weight);
	
		// 요게 기존 방식이지만?
		
		System.out.println("======================");
		// 나이, 이름, 체중이라는 속성을 가진 강아지를 만들어야겠다.
		// 객체를 만드려면 ? : 클래스의 생성(정의)이 우선이다.
		// 
		
		// 실체화 (인스턴스, 객체)  
		Dog dog1  = new Dog();
		dog1.age = 3;
		dog1.name = "초코";
		dog1.weight = 3.5;
		// 원하는 값에 설정
		System.out.println(dog1); // 주소값, 주소를 가지고 있으니 찾아갈수있다. 
		dog1.printInfo();
		
		// 2번째 강아지 
		// 나이가 2살, 이름이 요미, 체중 2.2
		Dog dog2 = new Dog(); // 실제 강아지 생성
		dog2.age = 2;
		dog2.name = "요미";
		dog2.weight = 2.2;
		dog2.printInfo();
		
		
		
		
		// 1번 짖게한다. (2번)
		dog2.bark(); //요미가 짖어따

		System.out.println("=====================");
		// 컴퓨터를 객체로 표현해주세요.
		// cpu 2.5, ram 8, hdd 256
		// printInfo() 정보 출력 가능

		// 컴 -> 객체 ? : 가장 먼저 해야할 것은 class 정의다.
		// 객체화 == 실체화 = 인스턴스화  >> new를 해달라는 말이고, new를 해야 객체가 생성
		
		Computer com = new Computer();
		com.cpu = 2.5;
		com.ram = 8;
		com.hdd = 256;
		com.printInfo();
		
		System.out.println("=====================");
		
		// 커피 
		// 가격, 커피 이름, 정보출력 기능  
		// 아메리카노 2000원 > 만들고 정보출력
		// 카페라떼 3000원 >  만들고 정보출력
		
		// 2개면 2개 생성
		
		Cafe ame = new Cafe(); //클래스 이름은 공통
		ame.name = "아메리카노";
		ame.price = 2000;
		ame.printiInfo();

		Cafe lat = new Cafe();
		lat.name = "카페라떼";
		lat.price = 3000;
		lat.printiInfo();
		
		
		
		
		
		
}
}
