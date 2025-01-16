import java.security.PublicKey;

public class Dog {

	//메소드 안에 소속되어있지 않은 것 > 파란색 > 전역변수 
	int age;
	String name;
	double weight;
	
	void bark() { 
		System.out.println("멍!");
	}
	
	public void printInfo() {
		System.out.println(name);
		System.out.println(age);
		System.out.println(weight);
	}
}
