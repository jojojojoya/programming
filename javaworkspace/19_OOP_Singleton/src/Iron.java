
public class Iron {

	
	
	private String name;
	private int age;
	private static final Iron Iron = new Iron("아이언맨",40);
	
	public static Iron getiron () {
		return Iron;
	}
	
	
	public Iron() {
	}
	
	public Iron(String name, int age) {
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
