import java.util.Random;

public class Enemy extends Random {

	
	public int chooseN() {
		
		 // 랜덤값 추출 1~100
		return super.nextInt(100) + 1;
		
		
	}
}
