import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CMain05 {
public static void main(String[] args) {
	
	// Map : 무조건 알아야 함
	// Map 계열 : HashMap : 가변 사이즈, 순서 시스템x, 키-값 쌍
	
	Map<String, Integer> hm = new HashMap<String, Integer>();
	hm.put("탄수화물", 10);
	hm.put("단백질", 20);
	hm.put("지방", 30);
	
	System.out.println(hm.get("단백질"));
	System.out.println("==========");
	
	
	// 찾고 싶은 학생이 있음
	// 찾을 학생의 이름을 쓰면 내용을 보여주세요.
	
	// 학생을 뭘로 찾을지?
	
	// 학생을 번호로 찾겠다. => list
	// 이름으로 찾겠다. => map
	
	
	
	HashMap<String, Student> students = new HashMap<String, Student>();
	
	students.put("sw", new Student("승완",25,92,82,72));
	students.put("dy", new Student("도윤",28,98,80,74));
	students.put("sj", new Student("수진",22,91,81,73));
	students.put("wh", new Student("원호",24,95,83,71));
	System.out.println(students.get("sj").getAge());
	
	// 누가 있는지 보여줘야함 (key)
	// 키 값만 추출 
	
	Set<String> sNames = students.keySet();
	for (String s : sNames) {
		System.out.println(s);
	}
	
	
	
	Scanner sc = new Scanner(System.in);
	System.out.println("누구? (이니셜)");
	String name = sc.next();
	System.out.println(students.get(name));
	
}
}
