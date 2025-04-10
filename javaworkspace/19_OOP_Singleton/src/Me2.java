
public class Me2 {
	
	private String name;
	private int age;
	private static Me2 me; // static final 상수
	
	
//	final ==> 상수로 쓸수있다
//	static final // 상수
	
	public static Me2 getMe() {
		if (me == null) {
				// me 인스턴스가 생성된 적 없다면
			me = new Me2("원호",25);
		}
		return me;
	}
	private Me2() {} //기본생성자 
	
	public Me2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return agㅌe;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}

