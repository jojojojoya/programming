
public class HAMain02 {
public static void main(String[] args) {
	
	// 회사
		// 사명, 설립연도, 사옥위치
		// 정보출력 - toString info
	
	//제품
		// 품명, 가격
		// 정보출력 - toString info 
	
	Company company = new Company("호호",1900,"서울");
	
	Product product = new Product("우왕고구마", 1000,company);
	System.out.println(product.hashCode());
	
	Product product2 = new Product("우왕고구마2", 2000, new Company("mz", 2025, "을지로"));
	System.out.println(product2);
}
}
