package com.mz.web;

//bean, VO, DTO, POJO
public class MemberDTO {

	// 결과에서 뭘 쓸지?
	// db table을 보고
	private int no;
	private String name;
	private int age;
	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(int no, String name, int age) {
		super();
		this.no = no;
		this.name = name;
		this.age = age;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	@Override
	public String toString() {
		return "MemberDTO [no=" + no + ", name=" + name + ", age=" + age + "]";
	}

	
	
	
	
	
	
}
