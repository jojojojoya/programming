import java.util.Random;

public class Box extends Random {
	
	
	@Override // 상속받은 메서드의 '재정의'
	public int nextInt(int bound) {
		int temp = super.nextInt(bound);
		if (temp % 2 == 0) {
			return temp;
		}
			return nextInt(bound);
		
		
	}
}



