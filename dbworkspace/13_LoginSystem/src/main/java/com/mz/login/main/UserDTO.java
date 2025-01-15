package com.mz.login.main;

public class UserDTO {
	private String id;
	private String pw;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDTO(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", getId()=" + getId() + ", getPw()=" + getPw()
				+ ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
}
