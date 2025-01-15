import java.util.ArrayList;
import java.util.Iterator;

		
		// 가변 배열 
		
public class CMain02 {
	public static void main(String[] args) {
		// List 계열 : 가변 사이즈 배열 - 가장 많이 사용
		//
		ArrayList al = new ArrayList();
		al.add(1);
		al.add(1.1);
		al.add("꾸");
		al.add("꼥");
		System.out.println(al.size());
	
		//	자료형에 상관없이 사용 가능 ==> 안씀.
		
		
		ArrayList<String> al2 = new ArrayList<String>();
		al2.add("ㅎㅎ");
		al2.add("><");
		al2.add("ㅡㅡ^");
		System.out.println(al2.size());
		System.out.println(al2.get(2)); // 출력
		
		
		for (String asd : al2) {
			System.out.println(asd);
			
		}
		
		//일반 배열이었으면 length, 가변 배열이면 size,get
		
		for (int i = 0; i < al2.size(); i++) {
			System.out.println(al2.get(i));
		}
		
		
		System.out.println("=======");
		al2.add("ㅎ"); // 언제든지 추가 가능 
		al2.add(0, "ㅎㅎ"); // 위치 지정 가능
		al2.set(1, "dfsfds"); // 수정 가능
		al2.remove(3); // 삭제
		
		for (String ss : al2) {
			System.out.println(ss);
		}
		System.out.println("=======");
		// 클래스명 : generic => 데이터 타입을 제한하는 것
					//	컴파일 단계에서 이미 오류 확인 가능
	
		// 정수 10,20 추가하고 다 출력
		ArrayList<Integer> al3 = new ArrayList<Integer>();
		al3.add(10);
		al3.add(20);
		al3.add(10);

		
		
		for (Integer it : al3) {
				System.out.println(it);

		
		}
	}
}
