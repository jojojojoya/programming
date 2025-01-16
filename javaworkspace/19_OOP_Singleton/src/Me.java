
public class Me {
	
	private String name;
	private int age;
	private static final Me ME = new Me("지원",25);
	
	public static Me getMe() {
		return ME;
	}
	private Me() {} //기본생성자 
	
	public Me(String name, int age) {
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
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}

