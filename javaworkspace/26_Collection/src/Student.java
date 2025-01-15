
public class Student {
	private String name;
	private int age;
	private int kor;
	private int eng;
	private int jp;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, int age, int kor, int eng, int jp) {
		super();
		this.name = name;
		this.age = age;
		this.kor = kor;
		this.eng = eng;
		this.jp = jp;
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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getJp() {
		return jp;
	}

	public void setJp(int jp) {
		this.jp = jp;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", kor=" + kor + ", eng=" + eng + ", jp=" + jp + "]";
	}

}
