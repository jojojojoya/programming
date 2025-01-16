import java.util.Scanner;

public class Guest {
		
	
		String name;
		double weight;
		double height;
		Scanner mouth;
		
		public void act2() {
				mouth = new Scanner(System.in);
				System.out.println("이름 : ");
				name = mouth.next();
				System.out.println("키 : ");
				height = mouth.nextDouble();
				System.out.println("체중 : ");
				weight = mouth.nextDouble();
		}
}
