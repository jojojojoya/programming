package com.hy.board.movie;

public class MovieDTO {
	
	private int m_no;
	private String m_title;
	private String m_actor;
	private String m_img;
	private String m_story;
	
	public MovieDTO() {
		// TODO Auto-generated constructor stub
	}

	public MovieDTO(int m_no, String m_title, String m_actor, String m_img, String m_story) {
		super();
		this.m_no = m_no;
		this.m_title = m_title;
		this.m_actor = m_actor;
		this.m_img = m_img;
		this.m_story = m_story;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getM_title() {
		return m_title;
	}

	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	public String getM_actor() {
		return m_actor;
	}

	public void setM_actor(String m_actor) {
		this.m_actor = m_actor;
	}

	public String getM_img() {
		return m_img;
	}

	public void setM_img(String m_img) {
		this.m_img = m_img;
	}

	public String getM_story() {
		return m_story;
	}

	public void setM_story(String m_story) {
		this.m_story = m_story;
	}

	@Override
	public String toString() {
		return "MovieDTO [m_no=" + m_no + ", m_title=" + m_title + ", m_actor=" + m_actor + ", m_img=" + m_img
				+ ", m_story=" + m_story + "]";
	}
	
	
	

}
