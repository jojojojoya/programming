package com.hy.board.review;

import java.util.Date;

public class ReviewDTO {

	
	private int r_no; 
	private String r_title; 
	private String r_text; 
	private Date r_date;
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int r_no, String r_title, String r_text, Date r_date) {
		super();
		this.r_no = r_no;
		this.r_title = r_title;
		this.r_text = r_text;
		this.r_date = r_date;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_text() {
		return r_text;
	}

	public void setR_text(String r_text) {
		this.r_text = r_text;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}


	




	
	
}
