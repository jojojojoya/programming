
// singleton pattern()  디자인 패턴 
// 객체를 유일하게 하나만 만들어 사용하고 싶을때  

public class SMAin {
	public static void main(String[] args) {

	// 자기 자신 : 이름, 나이
	// 
		Me me = Me.getMe(); 
		System.out.println(me);
		
		Me me2 = Me.getMe();
		System.out.println(me2);
		
		
		me.setAge(26);
		me.setName("dd");
		
		// 아이언맨 : 이름, 나이 -> 정보출력
		
	}
	
	
}
