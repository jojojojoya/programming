package com.sz.test;

public class Bean {

	private String name;
	private int age;
	private String gen;
	private String[] inter ;

	public Bean() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String[] getInter() {
		return inter;
	}

	public void setInter(String[] inter) {
		this.inter = inter;
	}

	public Bean(String name, int age, String gen, String[] inter) {
		super();
		this.name = name;
		this.age = age;
		this.gen = gen;
		this.inter = inter;
	}

	
}
