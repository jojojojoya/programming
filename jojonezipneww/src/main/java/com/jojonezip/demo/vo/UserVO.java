package com.jojonezip.demo.vo;



public class UserVO {
	private int user_no;
	private String user_id;
	private String user_nickname ;
	private String user_password;
	private int user_type;
	

	
	public UserVO() {
		// TODO Auto-generated constructor stub
	}


	public UserVO(int user_no, String user_id, String user_nickname, String user_password, int user_type) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_nickname = user_nickname;
		this.user_password = user_password;
		this.user_type = user_type;
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_nickname() {
		return user_nickname;
	}


	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}


	public String getUser_password() {
		return user_password;
	}


	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}


	public int getUser_type() {
		return user_type;
	}


	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

}