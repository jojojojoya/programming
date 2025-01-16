package com.mz.web;

public class MemberVO {

	private int m_no;
	private String m_name;
	private int m_age;
	
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}


	public int getM_no() {
		return m_no;
	}


	public void setM_no(int m_no) {
		this.m_no = m_no;
	}


	public String getM_name() {
		return m_name;
	}


	public void setM_name(String m_name) {
		this.m_name = m_name;
	}


	public int getM_age() {
		return m_age;
	}


	public void setM_age(int m_age) {
		this.m_age = m_age;
	}


	public MemberVO(int m_no, String m_name, int m_age) {
		super();
		this.m_no = m_no;
		this.m_name = m_name;
		this.m_age = m_age;
	}


	@Override
	public String toString() {
		return "MemberVO [m_no=" + m_no + ", m_name=" + m_name + ", m_age=" + m_age + ", getM_no()=" + getM_no()
				+ ", getM_name()=" + getM_name() + ", getM_age()=" + getM_age() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
