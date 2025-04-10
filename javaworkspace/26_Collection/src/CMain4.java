import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CMain4 {
public static void main(String[] args) {

	
	
	// List 계열 - ArrayList	: 가변 사이즈 배열
	// Set 계열 - HashSet 	: 가변 사이즈, 중복 자동제거, 순서 랜덤
	
	
	
	HashSet<Integer> hs = new HashSet<Integer>();
	hs.add(10);
	hs.add(20);
	hs.add(30);
	
	hs.add(10);
	hs.add(20);
	hs.add(30);
	
	// hashSet -> ArrayList로 
	List<Integer> list = new ArrayList<Integer>(hs); 
	
	//	섞기
	Collections.shuffle(list);
	for (Integer e1 : list) {
		System.out.println(e1);
	}
	

	System.out.println(hs.size());
	System.out.println("==================");
	
	
	for (Integer ii : hs) {
		System.out.println(ii);
		
	}
	// 사용하려면 set -> list 사용
	ArrayList<Integer> al = new ArrayList<Integer>(hs);
	System.out.println(al.get(0));
	
	System.out.println("===================");
	HashSet<Student> students = new HashSet<Student>();
	
	students.add(new Student("승완",25,92,82,72));
	students.add(new Student("도윤",28,98,80,74));
	students.add(new Student("수진",22,91,81,73));
	students.add(new Student("원호",24,95,83,71));
	students.add(new Student("원호",24,95,83,71));
	
	System.out.println(students.size());
	Student ss = new Student("승완",25,92,82,72);
	students.add(ss);
	students.add(ss);
	students.add(ss);
	students.add(ss);
	students.add(ss);
	System.out.println(students.size());
	for (Student mmm : students) {
		System.out.println(mmm);
		
	}

	Random r = new Random();
	HashSet<Integer> lotto = new HashSet<Integer>();
	
	while (true) {
		lotto.add(r.nextInt(45)+1);
		if (lotto.size() == 6) {
			break;
			
		}
	}
	for (Integer i : lotto) {
	
		System.out.printf(i + " ");
	}
		
	}
	
}	


