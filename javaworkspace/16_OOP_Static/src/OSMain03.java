
public class OSMain03 {
	public static void main(String[] args) {
		// 삼성
		
		// Galaxy  
		// 품명 : 갤럭시 플립4, 가격 110, 제조사 삼성
		// 품명 : 갤럭시 플립5, 가격 150, 제조사 삼성
		// 품명 : 갤럭시 10, 가격 20, 제조사 삼성

		// 1. 필드 '직접' 접근 안되게 >> 간접으로 찍어서 돌리기
		// 2. 제조사는 무조건 삼성 (상수) >> static final >> 상수 /완
		// 3. 모든 제품의 생성은 오버로딩 생성자로 / 
		// 4. 상품의 이름/가격은 수정 가능. 별도의 값 얻기도 가능하게.
		// 5. 상품정보 출력 기능. printInfo
		
		Samsung galFlip4 = new Samsung("갤럭시 플립4", 110); {
			galFlip4.printInfo();
			System.out.println("==================");
			
		Samsung galFlip5 = new Samsung("갤럭시 플립5", 150); {
			galFlip5.printInfo();
			
			System.out.println("==================");
	
		Samsung gal10 = new Samsung("갤럭시 10", 20); {
			gal10.printInfo();
			System.out.println("==================");
		
			System.out.println(gal10.getCnt());
			
			
		}
		
		
		
		
		
		// 총 생산된 갤럭시 몇대 ??
		
		
	}

}}}
