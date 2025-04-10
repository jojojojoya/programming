
public class ICMain {
	public static void main(String[] args) {
	
		// - 인스턴스 생성 시 값 넣어서 생성(전부) 	>> 오버로딩 
		// - 모든 필드는 직접 접근 x 		>> private
		// - 값 수정 안되게, 따로 값 얻을 필요도 없음		 >> getter, setter 필요없음 >> car를 부모로 	
		
		
		//스포츠카			ex) "람보르기니", "123가567"
			// 모델명, 차량번호 > 정보출력
		Sports sports = new Sports("람보르기니","123가567");
		sports.info();
		
		
		
		//택시 			ex) "소나타", "00나123"
			// 모델명, 차량번호, 기본요금 > 정보출력
		Taxi taxi = new Taxi("소나타", "00나123",5000);
		taxi.info();
		
		//버스			ex) "파란버스", "33다1111"
			// 모델명, 차량번호, 노선번호 > 정보출력
		
		Bus bus = new Bus("파란버스","33다1111",501);
		bus.info();
	
		
	}
}


// 기본 생성자, 오버로딩, 오버라이딩

