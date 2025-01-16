package com.mz.tag.bmi;

// bean, pojo, dto, vo
public class Bean {
	// 결과에서 뭘 쓸지?
	
	private String name;
	private double height;
	private double weight;
	private String fName;
	
	private String bmi2;
	private String status;
	
	 public Bean() {
		// TODO Auto-generated constructor stub
	}

	public Bean(String name, double height, double weight, String fName, String bmi2, String status) {
		super();
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.fName = fName;
		this.bmi2 = bmi2;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getBmi2() {
		return bmi2;
	}

	public void setBmi2(String bmi2) {
		this.bmi2 = bmi2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
	
}
