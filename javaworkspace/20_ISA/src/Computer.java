
public class Computer extends Product {
	private int cpu;
	private int ram;
	private int hdd;
	
	
	public Computer() {
	}



	public Computer(String name, int price, int cpu, int ram, int hdd) {
		super(name, price);
		this.cpu = cpu;
		this.ram = ram;
		this.hdd = hdd;
	}



	public int getCpu() {
		return cpu;
	}


	public void setCpu(int cpu) {
		this.cpu = cpu;
	}


	public int getRam() {
		return ram;
	}


	public void setRam(int ram) {
		this.ram = ram;
	}


	public int getHdd() {
		return hdd;
	}


	public void setHdd(int hdd) {
		this.hdd = hdd;
	}
	
	
	@Override
	void info() {
		super.info();
		System.out.println(cpu);
		System.out.println(ram);
		System.out.println(hdd);
	}
	
}
