import javax.swing.text.StyledEditorKit.ForegroundAction;

public class RS_Practice01 {
	public static void main(String[] args) {
	
	// 최대한 한보고 
		//이중 for문으로 구구단 다 출력
	
	
	for (int i = 1; i < 9; i++) {
		
		for (int j = 1; j < 10; j++) {
			
			System.out.printf("%d * %d = %d\n",i+1,j,((i+1)*j));
		}
			
		}
		
	
		// 가로로 출력
	
	//행 8개 열 9개

	
	System.out.printf("\n");

	for (int i = 1; i < 9; i++) {
		for (int j = 1; j < 10; j++) { // j를 충족할때까지 반복
			System.out.printf("%d * %d = %d\t",j+1,i,((j+1)*i));
		}
		System.out.printf("\n");
	}
	
}
	
	}





