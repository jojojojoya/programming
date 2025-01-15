package com.mz.product;

public class Product {
	String name;
	int price;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
	
	
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}


		
	}
	


