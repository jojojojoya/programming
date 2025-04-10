import java.util.Random;

public class IAMain03 {
public static void main(String[] args) {
	// 랜덤숫자(짝수만) 1~10 중
	Random r = new Random();
	int a = r.nextInt(10)+1;
	
	
	if (a % 2 == 0) {
		System.out.println(a);
	} else {
		System.out.println(a+1);
	}
	System.out.println("============");
	
	
	
	
	//짝수만 뽑아주는 박스
	Box b = new Box();
	System.out.println(b.nextInt(10));
	
}} 
