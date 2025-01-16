package com.mz.bmi;


// bean,pojo, dto, vo
public class Bean {
	//결과에서 뭘쓸지?
	private String name; 
	private double height; 
	private double weight;
	private String fName;
	private String bmi;
	private String status; 
	
	
	public Bean() {
		// TODO Auto-generated constructor stub
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


	public String getBmi() {
		return bmi;
	}


	public void setBmi(String bmi) {
		this.bmi = bmi;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Bean(String name, double height, double weight, String fName, String bmi, String status) {
		super();
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.fName = fName;
		this.bmi = bmi;
		this.status = status;
	}
	

}
