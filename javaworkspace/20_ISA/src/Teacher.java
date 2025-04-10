// teacher is a human (o) : 상속  
// 부모클래스(슈퍼클래스) = Human
// 자식클래스(서브클래스) = teacher


public class Teacher extends Human {

	
	String subject;
	
	@Override
	void info() {
		super.info();
		System.out.println(subject);
	}

	
}
