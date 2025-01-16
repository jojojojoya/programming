
public class Bus extends Car {

	private int lineNum;	

	public Bus() {
	}

	public Bus(String model, String number, int lineNum) {
		super(model, number);
		this.lineNum = lineNum;
	}

@Override
public void info() {
	// TODO Auto-generated method stub
	super.info();
System.out.println(lineNum);
}
}
	
	

