package com.ce.board.movie;

public class MovieDTO {

	private int no;
	private String title;
	private String actor;
	private String img;
	private String story;
	
	public MovieDTO() {
		// TODO Auto-generated constructor stub
	}

	public MovieDTO(int no, String title, String actor, String img, String story) {
		super();
		this.no = no;
		this.title = title;
		this.actor = actor;
		this.img = img;
		this.story = story;
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

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}
	
}
