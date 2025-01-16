
public class Phone extends Product{
	private double size;
	private int g;
	// 오버라이드는 상위클래스에서  상속받은 메소드를 수정하는 것 

	public Phone() {
	}


	public Phone(String name, int price, double size, int g) {
		super(name, price);
		this.size = size;
		this.g = g;
		
	}


	public double getSize() {
		return size;
	}


	public void setSize(double size) {
		this.size = size;
	}


	public int getG() {
		return g;
	}


	public void setG(int g) {
		this.g = g;
		
		
	
	}
	
	@Override
	void info() {
		super.info();
		System.out.println(size);
		System.out.println(g);
	}
	}

