package com.mz.conv;

public class Vo {

	
	private String color; 
	private String color2; 
	private String label; 
	private String label2; 
	private String str; 
	private double i;
	
	public Vo() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public Vo(String color, String color2, String label, String label2, String str, double i) {
		super();
		this.color = color;
		this.color2 = color2;
		this.label = label;
		this.label2 = label2;
		this.str = str;
		this.i = i;
	}

	
	
}
