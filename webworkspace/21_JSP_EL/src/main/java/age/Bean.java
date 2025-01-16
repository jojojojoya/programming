package age;

public class Bean {

	public Bean() {
		// TODO Auto-generated constructor stub
	}
	
	
	private int age;
	private int birth;
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public Bean(int age, int birth) {
		super();
		this.age = age;
		this.birth = birth;
	}
	
	
}
