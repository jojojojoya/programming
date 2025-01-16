
public class IAMain {
public static void main(String[] args) {
	//사람 = 이름, 나이     			자기소개 (정보출력기능)
			// 원호, 25
		Human human = new Human();
		human.name = "원호";
		human.age = 25;
		human.info();
		
		
	//학생 = 이름, 나이, 일본어점수     	자기소개 (정보출력기능)
			// 수진, 26, 99  
		Student student = new Student();
		student.name = "수진";
		student.age = 26;
		student.jpScore = 99;
		student.info();
		
		
	//선생 = 이름, 나이, 담당과목		    자기소개 (정보출력기능)
			// mz, 30, java  
		Teacher teacher = new Teacher();
		teacher.name = "mz";
		teacher.age = 30;
		teacher.subject = "java";
		teacher.info();
		
	
	
	
}
	
}
