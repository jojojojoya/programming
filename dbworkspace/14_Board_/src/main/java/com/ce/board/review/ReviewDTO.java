package com.ce.board.review;

import java.util.Date;

import com.google.gson.Gson;

public class ReviewDTO {


	private int no;
	private String title;
	private String txt;
	private Date date;
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int no, String title, String txt, Date date) {
		super();
		this.no = no;
		this.title = title;
		this.txt = txt;
		this.date = date;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "ReviewDTO [no=" + no + ", title=" + title + ", txt=" + txt + ", date=" + date + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public String toJsonByMe() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
