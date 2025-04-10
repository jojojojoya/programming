import java.util.Iterator;

public class AMain04 {
	public static void main(String[] args) {
		int aa[] = { 100, 200, 300, 400 }; // aa에 4개 수가 들어갔다
		int[] bb = { 100, 200, 300 }; // bb에 3개 수가 들어갔다
		int[] cc; 					
		cc = new int[]	{ 100, 200 }; // cc에 2개 수가 들어갔다
		int[] dd = new int [1]; // 뭔진모르지만 1개 있다. 
		dd[0] = 100; 
		
		// 결과 출력은 아래처럼 나오게, 반복문 4개를 써서 
		// aa[0] = 100 aa[1] = 200 aa[2] = 300 aa[3]= 400
		// bb[0] = 100 bb[1] = 200 bb[2] = 300
		// cc[0] = 100 cc[1] = 200
		// dd[0] = 100

		for (int i = 0; i < aa.length/*4*/; i++) {
			System.out.printf("aa[" + i + "] = " + aa[i] + " ");		}
		System.out.println();
		for (int b = 0; b < bb.length/*3*/; b++) {
			System.out.print("bb[" + b + "] = " + bb[b] + " ");		}
		
		System.out.println();
		for (int c = 0; c < cc.length/*3*/; c++) {
			System.out.print("cc[" + c + "] = " + cc[c] + " ");		}
		System.out.println();
		for (int d = 0; d < dd.length/*3*/; d++) {
			System.out.print("dd[" + d + "] = " + dd[d] + " ");		}
		
		/* 쌤 예시 
		for (int i = 0; i < aa.length; i++) {
			System.out.printf("aa[%d] = %d ", i, aa[i]);		}
		for (int i = 0; i < bb.length; i++) {
			System.out.printf("bb[%d] = %d ", i, bb[i]);		}
		for (int i = 0; i < cc.length; i++) {
			System.out.printf("cc[%d] = %d ", i, cc[i]);		}
		for (int i = 0; i < dd.length; i++) {
			System.out.printf("dd[%d] = %d ", i, dd[i]);		} 
		*/

	
		
	}}
