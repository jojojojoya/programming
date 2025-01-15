
public class OMain03 {
	public static void main(String[] args) {
		
		
		// 제품 squ
		// 품명, 가격, 원산지 name, price, made
		// 정보 출력 기능
		
		
		// 품명 : 보드마카, 가격 1000원, 한국 // 정보출력 
		// 품명 : 형광펜, 가격 500원, 중국 // 정보출력
		
		Squ boa = new Squ();
		boa.name =  "보드마카";
		boa.price =  1000;
		boa.made =  "한국";
		boa.printInfo();
		
		Squ high = new Squ();
		high.name =  "보드마카";
		high.price =  500;
		high.made =  "중국";
		high.printInfo();
		
		
		
				
		
	}
}
