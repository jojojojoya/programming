
public class Human {

	private String name;
	private int age;
	private String type;
	private Dog pet; // << dog의 정체가 class라는 게 중요 << class 타입에 값이 없으면 null이 들어가 있음
	
	public Human() {
	}


	public Human(String name, int age, String type) {
		super();
		this.name = name;
		this.age = age;
		this.type = type;

	}
	public Human(String name, int age, String type, Dog pet) {
		super();
		this.name = name;
		this.age = age;
		this.type = type;
		this.pet = pet;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}




	public Dog getPet() {
		return pet;
	}


	public void setPet(Dog pet) {
		this.pet = pet;
	}


	void info() {
		System.out.println(name);
		System.out.println(age);
		System.out.println(type);
		System.out.println("안녕하세요." + name + "입니다.");
		System.out.println("================");
		
		
		if (pet != null) { // 강아지가 없지 않다 > 있을때
		
		System.out.println(pet.getName()); 
		System.out.println(pet.getAge());
		System.out.println(pet.getType());
		} // pet.info();로 대체 가능 >> 강아지 정보출력 
	}

}
