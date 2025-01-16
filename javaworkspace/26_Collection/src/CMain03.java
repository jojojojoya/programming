import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CMain03 {
	public static void main(String[] args) {
	ArrayList<Student> students = new ArrayList<Student>(); 
	Student s = new Student("진현",20,90,80,70);
	System.out.println(s);
	
	
	students.add(s);
	students.add(new Student("승완",25,92,82,72));
	students.add(new Student("도윤",28,98,80,74));
	students.add(new Student("수진",22,91,81,73));
	students.add(new Student("원호",24,95,83,71));
	
	//학생수
	System.out.println(students.size());
	
	
	//첫번째 학생의 국어점수
	System.out.println(students.get(0).getKor());
	
	//두번째 학생의 이름
	System.out.println(students.get(1).getName());

	//세번째 학생의 일본어 점수
	System.out.println(students.get(2).getJp());

	//네번째 학생의 이름과 나이
	Student ss = students.get(3);

	System.out.println(ss.getName() + "," + ss.getAge());
	
	System.out.println("================");
	for (Student kk : students) {
		System.out.println(kk);
	}
	//다섯번째 국.영.일 평균점수
	Student kk = students.get(4);
	System.out.println((kk.getEng() + kk.getJp() + kk.getKor()) /3);
	
	System.out.println("================");
	
	
	
	// 나이순 정렬
	
	Comparator<Student> d = new Comparator<Student>() {
		
		@Override
		public int compare(Student o1, Student o2) {
			Integer o1Age = o1.getAge();
			Integer o2Age =	o2.getAge();
			
			return o1Age.compareTo(o2Age);
		}
	};
	
	students.sort(d); // 오름차순
	students.sort(d.reversed()); // 내림차순
	
	for (Student sss : students) {
		System.out.println(sss);
		
		

	}
	
	
	
	// 종합 성적순
	
	Comparator<Student> e = new Comparator<Student>() {
		
		@Override
		public int compare(Student o1, Student o2) {
			Double o1Avg = (o1.getKor() + o1.getEng() + o1.getJp()) / 3.0;
			Double o2Avg = (o2.getKor() + o2.getEng() + o2.getJp()) / 3.0;
			return o1Avg.compareTo(o2Avg);
		}
	};
	students.add(new Student("꼴등",20,1,1,1));	
	students.sort(e.reversed());
	System.out.println("============");
	for (Student ppp : students) {
		System.out.println(ppp);
	}
	
	System.out.println("======1등, 꼴등========");

		// 1등 정보 출력
	System.out.println(students.get(0));
	
	// 꼴등 정보 출력
	System.out.println(students.get(students.size()-1));

	
	
	}
}
