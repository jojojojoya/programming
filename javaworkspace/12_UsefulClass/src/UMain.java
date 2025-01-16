
public class UMain {
	public static void main(String[] args) {
		
		String s = "안녕";
		System.out.println(s);
		String s2 = "안녕";
		System.out.println(s.equals(s2)); // 문자열 비교
		System.out.println("==========");

		String ss = new String("이제 두시간만..");
		System.out.println(ss);
		
		String ss2 = "이제 두시간만.." ;
		System.out.println(ss.equals(ss2)); // 문자열 비교
		
		
		System.out.println("=========="); // 문자열 비교
		
		// stack(차곡차곡 쌓이는 것) / heap이라는 영역이 있음
		// 참조한다.
		
		// ss의 두번째 글자를 출력하시오.
		
		
		System.out.println(ss.charAt(4)); 
		
		// 1GB 
		//  
		// ss의 세번째 글자를 출력
		System.out.println(ss.charAt(2)); 

		
		// ss의 전체 글자 수 
		System.out.println(ss.length()); 

		System.out.println("=========="); // 문자열 비교
		// ss에서 '시'라는 글자가 몇번째에 있나? ==> 4번째 
			System.out.println(ss.indexOf("시")); 
		// ss가 '.'으로 끝나는가? ==> true
			System.out.println(ss.endsWith(".")); 
			
		// ss가 '요'로 끝나는가? ==> false
			System.out.println(ss.endsWith("요")); 
		
		// ss가 '이'로 시작하는가? ==> true
			System.out.println(ss.startsWith("이")); 
		
		// ss가 '두'로 시작하는가? ==> false
			System.out.println(ss.startsWith("두")); 
	
			System.out.println();
			// gpt x > 구글링 O
			
			// ss에서 '두시간만' -> '한시간만'으로 바꾸기 
			System.out.println(ss.replace("두시간만","한시간만")); 
			
			
			// ss에서 2~5번째 글자까지 출력하기 (1: 제 두시, 2: 제 한시) 
			System.out.println(ss.substring(1,5)); //수정한게 아니라, 한시간으로 바꾼상태에서 출력했을뿐
			System.out.println(ss.replace("두시간만", "한시간만").substring(1,5)); 
			
			String sss = ss.replace("두시간만", "한시간만");
			System.out.println(sss.substring(1,5)); //수정한게 아니라, 한시간으로 바꾼상태에서 출력했을뿐
			//  
			
			// 문자열 분리 ★★★★ 중요중요
			String sssk = "딸기,망고,바나나";
			String[] sssl = sssk.split(",");
			for (String lll /* 그냥 정의 */ : sssl) {
				System.out.println(lll);
			}
			
			// 문자열 붙이기 
			// 안 좋다. 

			String s1 = "첫";
			s1 += "번";
			s1 += "째";
			s1 += "시";
			s1 += "간";

			System.out.println(s1);
			
			
			
			//좋은 방법, 수정 가능한 문자열 객체
			StringBuffer sb = new StringBuffer(); // 객체가 생기고 주소가 생긴다. 
			String s22 = "첫";
			sb.append(s22);
			sb.append("번");
			sb.append("째");
			sb.append("시");
			sb.append("간");
			System.out.println(sb);
			System.out.println(sb.toString());
			
			StringBuilder sb2 = new StringBuilder();
			String s33 = "첫";
			sb2.append(s33);  //append 더해라
			sb2.append("번");
			sb2.append("째");
			sb2.append("시");
			sb2.append("간");
			System.out.println(sb2.toString());
			// StringBuilder, StringBuffer의 차이 
			// 빌더는 동기화 미지원, 버퍼는 동기화 지원하여 멀티스레드 환경에서 동작
			
	}
}

