package com.mz.product;

public class Computer extends Product {

	public int cpu;
	int ram;
	protected int hdd;

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	public Computer(String name, int price, int cpu, int ram, int hdd) {
		super(name, price);
		this.cpu = cpu;
		this.ram = ram;
		this.hdd = hdd;
	}

	@Override
	public void printInfo() {
		System.out.println(cpu);
		System.out.println(ram);
		System.out.println(hdd);
		super.printInfo();
	}

	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", ram=" + ram + ", hdd=" + hdd + ", name=" + name + ", price=" + price
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
