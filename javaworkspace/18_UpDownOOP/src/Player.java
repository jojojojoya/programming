import java.util.Scanner;

public class Player {

	
		Scanner mouth;
		
		public Player() {  //객체 인스턴스화 할때 메모리 할당하겠다. 
			mouth = new Scanner(System.in);
			
		}
		public int say()
		{	 
			return mouth.nextInt();
		}
}
