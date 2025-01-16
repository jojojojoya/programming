package com.mz.dbweb;

public class Human {
	//결과 화면에서 뭘 쓸지 생각해라
	
	private String name;
	private int age;
	
	public Human() {
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

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
}
