import java.util.Random;

import com.mz.product.Computer;
import com.mz.product.Printer;
import com.mz.product.Scanner;

public class PMain extends Computer{
	
	
		@Override
		public void printInfo() {
			// TODO Auto-generated method stub
			super.printInfo();
		}
	
		
public static void main(String[] args) {
		// 프린터
			// 이름, 가격 
		Printer p = new Printer("프린터기기",50);
		System.out.println(p);
		
		// 스캐너
			// 이름, 가격 
		Scanner s = new Scanner("스캐너", 39);
		System.out.println(s);
		java.util.Scanner sc = new java.util.Scanner(System.in); 
		// 클래스 이름이 같다면 구분지어줄 필요갸 있음 
		Random r = new Random();
		
		// java.lang      import 필요없음.
		String name = "asd";
		java.lang.String str = new String("dfsdfs");
		
		
		// 컴퓨터
			// 이름, 가격, cpu, ram, hdd
		
		Computer com = new Computer();
		com.cpu = "17"; //퍼블릭이라 나옴
		com.ram = 1; // default >> 같은 패키지여야먄 접근 가능
		com.hdd = 11; // protected
			// 내가 어디에서 쓰려고 하는지 // pmain과 상속관계가 없기 때문에 사용 불가  
		
		PMain pp = new PMain();
		pp.hdd = 1;
		
	}
}
