import java.util.Scanner;

public class Operator01 {
	public static void main(String[] args) {
		// import 단축키 : ctl + sft + O
		// 키보드 입력 받을 준비

		Scanner keyboard = new Scanner(System.in);
		
		// 대입 연산자 : =   <- 같다는 뜻이 아니다. 오른쪽 걸 왼쪽에 담았다는 뜻 
		// ==  : 같다는 뜻 
		int x = 10;
		
		// 산술연산자 : + - * / % 
		System.out.println("y값 : ");
	/* int y = keyboard.nextInt();
		
		System.out.println(x + y);
		
		int hap = x + y;
		int cha = x - y;
		int gop = x * y;
		int mok = x / y;
		int p = x % y; 
		System.out.println(p); // 나머지 */
		
		
		
		// *****리온한테 물어보깅 복습
		
		
		System.out.println("************");
		// 대입 연산자 : +=, *=, -=,/=, %=
		
		System.out.println(x);
		
		x =   x + 1;   // x에 x+1을 담았다.
        System.out.println(x);

        x += 1;        // x = x + 1;
        System.out.println(x);

        x -= 2;        // x = x - 2;
        System.out.println(x);

        x *= 2;        // x = x * 2;
        System.out.println(x);

        x /= 2;        // x = x / 2;
        System.out.println(x);

        x %= 2;			// x = x % 2;
        System.out.println(x);

        // 증감 연산자
        x++;    // 1증가        ++x도 가능한데 기능이 조금 다름
        System.out.println(x);

        x--;    // 1감소
        System.out.println(x);
        
        x++;
        System.out.println(x);
     
        
        
	}
}



