
public class IAMain02 {
	public static void main(String[] args) {
		
	// - 필드의 멤버변수에 직접 x 접근하지 못하도록, private 써라 
	// - 나중에 값 수정 가능하게 (setter), 생성할 때 값 세팅도 가능하게 (오버로딩 생성자)
		
		
	// 컴퓨터 : 품명, 가격, cpu, ram, hdd
		// 정보출력기능
	Computer com = new Computer("추웡컴",100,1,2,3);
	com.info();
	System.out.println("======");
	Computer com2 = new Computer();

	com2.setPrice(100);
	com2.setName("좋은컴");
	com2.setCpu(1);
	com2.setHdd(2);
	com2.setRam(3);
	com2.info();
	
	// 핸드폰 : 품명, 가격, 디스플레이사이즈, 무게
		// 정보출력기능
	
	Phone phone = new Phone("ip",150,33,10);
	phone.info();
			
	// 펜 : 품명, 가격
		// 정보출력기능
		
	Pen pen = new Pen("ip",150);
	pen.info();
// 	pen.setName(null);	물려받았기 때문에 쓸수 있었다.
	// 슈퍼클래스에서 private 걸면 상속이 안될텐데
	// 어떻게 값이 세팅이 되고, 정상동작까지 하는걸까?
	
	
	// 서술 작성
	
	// 1. 오버로딩 생성자 사용의 경우 :
	// 상속을 못받은 필드와 상관없이 부모의 오버로딩 생성자에 접근이 가능 (super(name,age));>> 공통된 부분을 불러서 쓸수있게 접근하려고 
	
	
	// 2. setter 사용 값 세팅의 경우 : 
	// 필드는 상속받지 못했지만 부모의 getter, setter는 다 상속받음
	// 
	
	
	
	
	
	
		
}
	
	
}
