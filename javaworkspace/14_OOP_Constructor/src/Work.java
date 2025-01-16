
public class Work {
	String name;
	int age;
	Boolean niw;

	public Work(String name, int age, Boolean niw) {
		super();
		this.name = name;
		this.age = age;
		this.niw = niw;
	}
	void printInfo() { //기능
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("야근 여부 : " + niw);
	}	
	
	String work(String what) {
		if (what.equals("일")) { 
			return "개발하기";
		} else if (what.equals("퇴근")) {
		}		return "ㄳ";} 
		
		void work() { // 기능
			System.out.println("zz..");
}}