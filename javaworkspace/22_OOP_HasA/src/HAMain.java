
public class HAMain {
public static void main(String[] args) {
	
	
	
	// 사람
		// 이름, 나이, 성별, 자기소개
	
		Human human = new Human("수진", 22, "여");
		human.info();
		
		System.out.println("===========");
		
	// 강아지
		// 이름, 나이, 종, 정보출력

		// 슈퍼클래스 없음
	
		Dog dog = new Dog("마루", 22, "푸들");
		dog.info();
		System.out.println("===========");
		
	
		Human human2 = new Human("혜윤", 22, "여", dog);
		human2.info();
		

		System.out.println("===========");
		// 자기소개 기능. 		값 세팅이 잘 되어있는지를 봐야함 
		// 주소값 
		
		
		Test test = new Test();
		test.no = 1;
		test.title = "안녕";
		test.content = "조수진이얌";
		System.out.println(test);


}}
