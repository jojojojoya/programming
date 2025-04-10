package com.mz.accessmf; 
	// 접근 제어자, 접근 제한자 

public class Human {
	public String name;
	protected int age;
	private String addr;
	private String birth;
	
	public void info() {
	System.out.println(name);
	System.out.println(age);
	System.out.println(addr);
	System.out.println(birth);
	}
	
}
