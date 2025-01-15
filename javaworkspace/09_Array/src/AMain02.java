
public class AMain02 {
	private static char[] kkk;

	public static void main(String[] args) {
		// 학생들의 일본어 점수 >> 배열
		int jpScore2 = 90;

		// 	학생 5명의 점수
		// 	들어올 값을 모를 때 (요소, Element)
		int[] jpScore = new int[5];  
		//	5칸짜리 배열 생성 
						// (위치, 색인, index)
		
		
		jpScore[0] = 100;
		jpScore[1] = 90;
		jpScore[2] = 80;
		jpScore[3] = 70;
		jpScore[4] = 60;
		
		System.out.println("네번째 학생의 점수 : " + jpScore[3]);
		//마지막 학생의 점수
		
		System.out.println("다섯번째 학생의 점수 : " + jpScore[4/*index*/]);
		System.out.println("===========================================" );
		
		
		
		// 값을 이미 알고 있을 때
//		int engTest[] = new int[] {10,20,30,40,50};
		int engTest[] = {10,20,30,40,50,1,1}; // 위와 동일
		System.out.println(engTest[1]);
		System.out.println(engTest.length /*length > 박스 갯수 말함*/);
	
		System.out.println("1. ===========================================" );
		
		
		// 학생 5명의 일본어 점수 50-90 jpTest
		// 배열의 길이 출력 (몇칸?)
		// 전체 평균점수 계산해서 출력
		
		int jpTest[] = {50,60,70,80,90};
		int ttl = (jpTest[0] +jpTest[1] + jpTest[2] + jpTest[3]+ jpTest[4])/5;
		
		System.out.println(jpTest.length);
		System.out.println("전체 평균 점수는 " + ttl + "입니다.");

		System.out.println("2. ===========================================" );

		for (int i = 0; i < jpTest.length; i++) {
			System.out.println(jpTest[i]);

			
			
			System.out.println("3. ===========================================" );

		// 베프 - 증감개념 없이 순차적으로 빠져나온다.
			
			for (int asd : jpTest) {
				System.out.println(asd); // index 활용해야 한다면 일단 foreach 쓰지만, 조건이 세부적으로 걸렸을때는 for 문 쓴다
				
				System.out.println("3. ===========================================" );
				// jpScore 2번째 학생점수
				
				System.out.println(jpScore[1]);
				// jpScore 몇칸짜리 배열?
				System.out.println(jpScore.length);
				// for로 하나씩 다출력
				for (int j = 0; j < jpScore.length; j++) {
					System.out.println(jpScore[j]);
					
				}
				// for each로 하나씩 다출력
				for (int abb : jpScore) {
					System.out.println(abb);
				}
				
				// 민석, 민재, 지훈 저장 /for, foreach로 다출력
				
				
				String[] name = {"민석", "민재", "지훈"};
				System.out.println(name.length);
				for (int j = 0; j < name.length; j++) {
					System.out.println(name[j]);
					
				}
				
				for (String naam : name) {
					System.out.println(naam);
					
				}
				System.out.println("4. ===========================================" );
			
				
				
				// 	5칸 짜리 배열 ?
				int push[] = new int [5];
				for (int i1 = 0; i1 < push.length; i1++) {
					// 배열 만들고 초기화 안하면 0이 자동으로 들어가 있음 
					System.out.println(push[i1]);
					
				}
				/* double - 0.0
				double aa[] = new double[5];
				for (int i1 = 0; i1 < aa.length; i1++) {
					// 배열 만들고 초기화 안하면 0이 자동으로 들어가 있음 
					System.out.println(aa[i1]); */
					
			/* String - 0.0
			String aa[] = new String[5];
			for (int i1 = 0; i1 < aa.length; i1++) {
				// 배열 만들고 초기화 안하면 0이 자동으로 들어가 있음 
				System.out.println(aa[i1]); */
				
				
				/*
			push[0] = 1;
			push[1] = 2;
			push[2] = 3;
			push[3] = 4;
			push[4] = 5;
				 */			
				
				
				for (int k = 0; k < push.length; k++) {
					push[k] = k + 1;
					System.out.println(push[k]);
				}
			
				System.out.println("5. ===========================================" );
			
				/// 저장된 것 다 더하기
				
				int ttlNew = 0;
				for (int k = 0; k < push.length; k++) {
					ttlNew += push[k];
			
					System.out.println(ttlNew);
					
					
				int ttlNew2 = 0;
				for (int yyy : push) {
					ttlNew2 += yyy;
				}
			
				System.out.println("6. ===========================================" );
				// JPTEST 평균값 출력 for / foreach 출력

				int ttls = 0;
				for (int j = 0; j < jpTest.length; j++) {
					ttls += jpTest[j] /jpTest.length;
				}
				System.out.println("평균 값 = " + ttls);
			
				int ttlt = 0;
				for (int amu : jpTest) {
					ttlt += amu/ jpTest.length;

				}
				System.out.println("평균 값 = " + ttlt);
				}
				
				
		}}}}
			
		
		
			
	
