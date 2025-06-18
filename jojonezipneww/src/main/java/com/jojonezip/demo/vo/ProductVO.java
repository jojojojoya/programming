package com.jojonezip.demo.vo;

public class ProductVO {

	private int product_id;
	private int product_amount;
	private int product_price;
	private String product_name;
	private String product_image;
	private String product_introtext;
	private String product_category;
	private String product_infocontent;

	// Getter & Setter
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_infocontent() {
		return product_infocontent;
	}

	public void setProduct_infocontent(String product_infocontent) {
		this.product_infocontent = product_infocontent;
	}

	public int getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public String getProduct_introtext() {
		return product_introtext;
	}

	public void setProduct_introtext(String product_introtext) {
		this.product_introtext = product_introtext;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
}
