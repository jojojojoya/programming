package com.mz.tag.format;

import java.util.Date;

public class Snack {
	private String name;
	private int price;
	private Date jejo;
	
	public Snack() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getJejo() {
		return jejo;
	}

	public void setJejo(Date jejo) {
		this.jejo = jejo;
	}

	public Snack(String name, int price, Date jejo) {
		super();
		this.name = name;
		this.price = price;
		this.jejo = jejo;
	}
	
	
}
