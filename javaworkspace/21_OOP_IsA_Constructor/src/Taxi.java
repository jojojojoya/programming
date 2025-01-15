
public class Taxi extends Car {

	private int bill;

	
	public Taxi() {
	}
	
	public Taxi(String model, String number, int bill) {
		super(model, number);
		this.bill = bill;
	}	
	
	
	@Override
	public void info() {
		super.info();
		System.out.println(bill);
		
	}}
	
