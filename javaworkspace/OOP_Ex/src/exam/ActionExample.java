package exam;

interface Action{
	void work();
	
}


public class ActionExample {
public static void main(String[] args) {
	
	
	Action action = new Action() {
		
		@Override
		public void work() {
			// 재정의해서 버리겠다 
			System.out.println("작업");
		}
	};
	
	action.work();
}
}
