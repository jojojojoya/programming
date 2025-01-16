import java.security.PublicKey;

public class Person {

	String name; 
	int age;
	
	
	// 기본 생성자 . -> 클래스 이름이랑 똑같이 생긴 메서드
	// 생성자를 전혀 안건드리면 자동으로 만들어줌. -> 클래스 이름이랑 똑같이 생긴 메서드
	// 오버로딩 생성자 만들었으면 자동으로 안만들어줌. -> 겹쳐진 상태를 부른다. 
	// 안보여도 있는 것과 같음. 근데 사람등장 / 이름 등을 세팅해야 되니까 가시화 시킨 것
	
	public Person() {
		System.out.println("사람 등장!");
		name = "jo";
		age = 1;
	}

	public Person(String name, int age) 	{
		
		// 기본생성자와 오버로딩 생성자가 둘다 있어야함 
		this.name = name; 
		this.age = age;
		
	}
		
	}
}
